import os
from PIL import Image
import numpy as np
import json
from sklearn.svm import SVR
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
import tensorflow as tf

def read_survey_results(filepath):
    with open(filepath, 'r') as json_file:
        survey_results = json.load(json_file)
    return survey_results

base_path = './ml/train'
beauty_levels = [str(i) for i in range(2, 10)]

path_to_original_images = base_path + '/original'
path_to_augmentations = base_path + '/augmentations'

def load_images_from_folder(data_path, label_path):
    images = []
    labels = []
    name_to_label_dict = read_survey_results(label_path)
    for level in beauty_levels:
        for filename in os.listdir(data_path + '_' + level):
            img_path = os.path.join(data_path + '_' + level, filename)
            if os.path.isfile(img_path):
                img = Image.open(img_path)
                if img is not None:
                    # Convert the image to the desired format (e.g., RGB)
                    
                    images.append(np.array(img))
                    labels.append(name_to_label_dict[filename[:-4]])

    return np.array(images), np.array(labels)

def normalise_data(data):
    scaler = StandardScaler()
    return scaler.fit_transform(data)

# Load images from the two folders
original_images, original_labels = load_images_from_folder(path_to_original_images, base_path+'/labels.json')
augmented_images, augmented_labels = load_images_from_folder(path_to_augmentations, base_path+'/artificial_labels.json')

X_train = np.concatenate((original_images, augmented_images), axis=0)
y_train = np.concatenate((original_labels, augmented_labels), axis=0)

# Shuffle the data (optional)
shuffle_indices = np.random.permutation(len(X_train))
X_train = X_train[shuffle_indices]
y_train = y_train[shuffle_indices]


num_samples, height, width, channels = X_train.shape
X_train = X_train.reshape((num_samples, height * width * channels))

# Create a StandardScaler instance
scaler = StandardScaler()


# Fit the scaler to the training data and transform the data
X_train = scaler.fit_transform(X_train)

with open('./ml/models/scalerProps.json', 'w') as json_file:
    json.dump({
        'means': scaler.mean_.tolist(),
        'scale': scaler.scale_.tolist()
    }, json_file)

# If needed, you can reshape the normalized data back to its original shape
X_train = X_train.reshape((num_samples, height, width, channels))

X_train, X_test, y_train, y_test = train_test_split(X_train, y_train, test_size=0.2, random_state=42)

print('a')
conv_base = tf.keras.applications.ResNet50(
    weights='imagenet',
    include_top=False,
    input_shape=(200, 200, 3)
)

model = tf.keras.models.Sequential()

model.add(conv_base)
model.add(tf.keras.layers.Flatten())
model.add(tf.keras.layers.Dense(128, activation="relu"))
model.add(tf.keras.layers.Dropout(0.1))
model.add(tf.keras.layers.Dense(64, activation='relu'))
model.add(tf.keras.layers.Dropout(0.3))
model.add(tf.keras.layers.Dense(1, activation='linear'))

for layer in conv_base.layers[:]:
   layer.trainable = False

model.compile(optimizer='adam', loss='mean_squared_error', metrics=['mean_squared_error'])

model.fit(X_train, y_train, epochs=20, validation_data=(X_test, y_test), batch_size=64)
model.save('./ml/models/beautiful_model_v1.h5')

for layer in conv_base.layers[165:]:
   layer.trainable = True

model.compile(optimizer='adam', loss='mean_squared_error', metrics=['mean_squared_error'])
model.fit(X_train, y_train, epochs=20, validation_data=(X_test, y_test), batch_size=64)
model.save('./ml/models/beautiful_model_v2.h5')