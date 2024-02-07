from sklearn.cluster import KMeans

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

  # if tally is greater than 5 means that on it matched at least in one of the color palettes
  return True