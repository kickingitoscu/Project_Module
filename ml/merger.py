
import random
import os
import json


def get_image_paths(directory):
    image_paths = []

    # Iterate over all files in the directory
    for filename in os.listdir(directory):
        file_path = os.path.join(directory, filename)

        # Check if the file is a regular file and has a common image file extension
        if os.path.isfile(file_path) and file_path.lower().endswith('.jpg'):
            image_paths.append(file_path)

    return image_paths

def read_survey_results(filepath):
    with open(filepath, 'r') as json_file:
        survey_results = json.load(json_file)
    return survey_results

def write_artificial_labels(filepath, labels_dict):
    with open(filepath, 'w') as json_file:
        json.dump(labels_dict, json_file)

base_path = './ml/train'
beauty_levels = [str(i) for i in range(2, 10)]

path_to_original_images = base_path + '/original'
path_to_augmentations = base_path + '/augmentations'

original_labels = read_survey_results(base_path + '/labels.json')

augmented_labels = {}
for level in beauty_levels:
    current_path = path_to_augmentations + f'_{level}'
    for path in get_image_paths(current_path):
        filename = path.replace(current_path+'/', '')
        original_label = original_labels[filename.split('_')[0]] 

        reduction_factor = random.uniform(0.01, 0.15)
        artificial_label = original_label - (original_label * reduction_factor)
        augmented_labels.update({filename[:-4]: artificial_label})

write_artificial_labels('./ml/train/artificial_labels.json', augmented_labels)