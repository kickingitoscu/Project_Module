import os
from PIL import Image
import numpy as np
import json
import sys
from itertools import chain
import warnings

from sklearn.preprocessing import StandardScaler

from color import check_resonation

# from sklearn.preprocessing import StandardScaler
# from sklearn.model_selection import train_test_split
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
warnings.filterwarnings("ignore")
import tensorflow as tf


image_path = sys.argv[1]
colors_to_prevent = np.load('./ml/models/color_palette.npz')['colors']
images_without_resonation = []

def load_images_from_folder(data_path):
    properties = []
    for filename in os.listdir(data_path):
        img_path = os.path.join(data_path, filename)
        if os.path.isfile(img_path):
            img = Image.open(img_path)
            if img is not None:
                # Convert the image to the desired format (e.g., RGB)
                img = np.array(img.resize((200,200)))
                if not check_resonation(img, colors_to_prevent):
                    images_without_resonation.append(filename)
                properties.append({
                    'name': filename,
                    'data': img
                })

    return properties


images_properties = load_images_from_folder(image_path)

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

model = tf.keras.models.load_model('./ml/models/beautiful_model_v2.h5')

infered_ranks = list(chain(*model.predict(images, verbose=0).tolist()))
results = {}
for props, rank in zip(images_properties, infered_ranks):
    results[props['name']] = rank if props['name'] not in images_without_resonation else 0.75 * rank

print(json.dumps(results))