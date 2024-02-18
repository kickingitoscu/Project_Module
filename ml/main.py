import os
from PIL import Image
import numpy as np
import json
import sys
from itertools import chain

from sklearn.preprocessing import StandardScaler

# from sklearn.preprocessing import StandardScaler
# from sklearn.model_selection import train_test_split
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
import tensorflow as tf


image_path = sys.argv[1]

def load_images_from_folder(data_path):
    properties = []
    for filename in os.listdir(data_path):
        img_path = os.path.join(data_path, filename)
        if os.path.isfile(img_path):
            img = Image.open(img_path)
            if img.mode != 'RGB':
                img = img.convert("RGB")
            if img is not None:
                # Convert the image to the desired format (e.g., RGB)
                img = np.array(img.resize((200,200)))
                properties.append({
                    'name': filename,
                    'data': img
                })

    return properties

def from_list_to_array(image_list):
    return np.array([image_prop['data'] for image_prop in image_list])

def get_unique_indices(images):
    data = from_list_to_array(images)
    flattened_images = data.reshape(data.shape[0], -1) 
    _, unique_indices = np.unique(flattened_images, axis=0, return_index=True)

    return unique_indices.tolist()

images_properties = load_images_from_folder(image_path)
images_properties = [images_properties[i] for i in sorted(get_unique_indices(images_properties))]

with open('./ml/models/scalerProps.json', 'r') as json_file:
    scaler_props = json.load(json_file)

scaler = StandardScaler()
scaler.mean_ = np.array(scaler_props['means'])
scaler.scale_ = np.array(scaler_props['scale'])

height, width, channels = images_properties[0]['data'].shape
images = scaler.transform(
    np.array([image_prop['data'].reshape((height * width * channels)) for image_prop in images_properties])
)
images = images.reshape((len(images_properties), height, width, channels))
model = tf.keras.models.load_model('./ml/models/final_v2.h5')

infered_ranks = list(chain(*model.predict(images, verbose=0).tolist()))
results = {}
for props, rank in zip(images_properties, infered_ranks):
    results[f'{os.path.basename(image_path[:-1])}/{props["name"]}'] = round(rank, 3)

print(json.dumps(results))