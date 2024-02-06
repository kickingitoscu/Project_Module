import os
from PIL import Image
import numpy as np
import json
import sys

from sklearn.preprocessing import StandardScaler

# from sklearn.preprocessing import StandardScaler
# from sklearn.model_selection import train_test_split
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
import tensorflow as tf


image_path = './ml/train/original_6'

def load_images_from_folder(data_path):
    images = []
    for filename in os.listdir(data_path):
        img_path = os.path.join(data_path, filename)
        if os.path.isfile(img_path):
            img = Image.open(img_path)
            if img is not None:
                # Convert the image to the desired format (e.g., RGB)
                
                images.append(np.array(img.resize((200,200))))

    return np.array(images)


# # Load images from the two folders
images = load_images_from_folder(image_path)
print(images.shape)

with open('./ml/models/scalerProps.json', 'r') as json_file:
    scaler_props = json.load(json_file)

scaler = StandardScaler()
scaler.mean_ = np.array(scaler_props['means'])
scaler.scale_ = np.array(scaler_props['scale'])

num_samples, height, width, channels = images.shape
images = images.reshape((num_samples, height * width * channels))
images = scaler.transform(images)
images = images.reshape((num_samples, height, width, channels))

model2 = tf.keras.models.load_model('./ml/models/beautiful_model_v2.h5')


predictions = model2.predict(images)
with open('./prediction.json', 'w') as json_file:
    json.dump({'res': predictions.tolist()}, json_file)