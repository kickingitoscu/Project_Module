import os
from PIL import Image
import numpy as np
import json

import Augmentor


for beauty_level in [str(i) for i in range(2, 10)]:
    pipeline = Augmentor.Pipeline(
        f'./ml/train/original_{beauty_level}',
        f'./../augmentations_{beauty_level}'
    )

    # Add the desired augmentations
    pipeline.rotate(probability=0.7, max_left_rotation=5, max_right_rotation=5)
    pipeline.flip_left_right(probability=0.3)
    pipeline.zoom_random(probability=0.5, percentage_area=0.9)
    pipeline.random_distortion(0.3, 5, 5, 6)
    pipeline.skew(0.3, 0.2)

    num_augmented_images = 1000

    pipeline.sample(num_augmented_images)
    output_path = f'./ml/train/augmentations_{beauty_level}'

    for filename in os.listdir(output_path):
        if filename.endswith(".jpg"):
            if filename.startswith(f"original_{beauty_level}_original_"):
                old_path = os.path.join(output_path, filename)
                filename = filename.replace(f"original_{beauty_level}_original_", "").replace(".jpg", "", 1)
                new_path = os.path.join(output_path, filename)
                os.rename(old_path, new_path)
            
            augmented_image_path = os.path.join(output_path, filename)
            
            # Open the image
            image = Image.open(augmented_image_path)
            
            # Convert the image to a NumPy array
            image_array = np.array(image)
            
            # Add Gaussian noise
            noise = np.random.normal(loc=0, scale=25, size=image_array.shape)
            noisy_image_array = np.clip(image_array + noise, 0, 255).astype(np.uint8)
            
            # Convert the NumPy array back to an image
            noisy_image = Image.fromarray(noisy_image_array)
            
            # Save the noisy image
            noisy_image.save(augmented_image_path)