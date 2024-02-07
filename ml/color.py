from sklearn.cluster import KMeans
import os
from PIL import Image
import numpy as np


def get_dominant_colors_of_image(image, amount_of_colors = 5):
    image = image.reshape((image.shape[0] * image.shape[1], 3))
    kmeans = KMeans(n_clusters = amount_of_colors)
    kmeans.fit(image)
    
    return kmeans.cluster_centers_.astype(int)

def check_resonation(image, colors_to_prevent):
  color_palette = get_dominant_colors_of_image(image, len(colors_to_prevent))
  tally = 0
  for color_index in range(len(color_palette)):    
    for color_to_prevent_index in range(len(color_palette)):
      for image_color, color_to_prevent in zip(color_palette[color_index], colors_to_prevent[color_to_prevent_index]):
        if image_color in range(
            (color_to_prevent-20 if color_to_prevent > 20 else 0), 
            (color_to_prevent+20 if color_to_prevent < 236 else 255)
          ):
            tally += 1
      if tally == 3:
         return False
      tally = 0
  
def detect_images_without_resonation(folder_path, colors_to_prevent):
  to_reject = []
  for filename in os.listdir(folder_path):
     img = Image.open(os.path.join(folder_path, filename))
     img = img.resize((224, 224))
     if check_resonation(np.array(img), colors_to_prevent):
        to_reject.append(filename)
  return to_reject
