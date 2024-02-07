from color import detect_images_without_resonation
from face_detection import process_images_in_folder
import numpy as np
import sys
import itertools
import warnings
warnings.filterwarnings("ignore")

colors_to_prevent = np.load('./ml/models/color_palette.npz')['colors']

folder_path = sys.argv[1]

_, images_with_faces = process_images_in_folder(folder_path)
images_without_resonation = detect_images_without_resonation(folder_path, colors_to_prevent)

print(list(set(itertools.chain(images_with_faces, images_without_resonation))))