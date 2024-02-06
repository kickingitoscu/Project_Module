import os 
from PIL import Image
import json
import math

def read_survey_results(filepath):
    with open(filepath, 'r') as json_file:
        survey_results = json.load(json_file)
    return survey_results


def form_dataset(root_folder):
    images = {}
    original_labels = read_survey_results('./ml/train/labels.json')
    def retrieve_image_names(one_folder_images):
        images_dict = {}
        
        for index, path in enumerate(one_folder_images):
            label = path.split(os.path.sep)[-2].split('_')

            label[2] = label[2] if label[2] == 'Grey' else label[2][0]
            label[3] = label[3][-2]
            label = ''.join(label) + f'[q{index+1}]'
            beauty_value = math.floor(original_labels[label])

            images_dict[label] = {
                'path': path,
                'beauty_level': str(beauty_value)
            }

        return images_dict

    for root, dirs, files in os.walk(root_folder):
        folder_list = []
        for file in files:
            # Check if the file has an image extension
            if file.lower().endswith(('.png', '.jpg', '.jpeg', '.gif', '.bmp')):
                folder_list.append(os.path.join(root, file))
        if len(folder_list) > 0:
            images.update(
                retrieve_image_names(
                    sorted(
                        folder_list,
                        key=lambda path: int(path[-7] + path[-5])
                    )
                )
            )
    if len(images.keys()) > 0:
        return images
    else:
        raise ValueError('No images gathered')


image_buckets = [{
    'low': [],
    'medium': [],
    'high': []
}
]
image_properties = form_dataset('./ml/raw_data')

train_data_dir = './ml/train/original_'
for level in range(2,10):
    os.makedirs(train_data_dir + str(level), exist_ok=False)

for image_name, properties in image_properties.items():
    _, extension = os.path.splitext(properties['path'])
    new_filename = f"{image_name}{extension}"
    new_path = os.path.join(train_data_dir + properties['beauty_level'], new_filename)
    
    with Image.open(properties['path']) as img:
        # Resize the image (you can adjust the size as needed)
        resized_img = img.resize((200, 200))

        # Save the resized image to the new path
        resized_img.save(new_path)