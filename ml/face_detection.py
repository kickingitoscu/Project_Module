from PIL import Image, ImageDraw
import face_recognition
import os
import sys

# Function to detect faces in an image
def detect_faces(image_path):
    image = face_recognition.load_image_file(image_path)
    faces = face_recognition.face_locations(image)
    return faces

def separate_image(image_path):
    # Open the image
    img = Image.open(image_path)

    # Detect faces in the image using the face_recognition library
    face_locations = detect_faces(image_path)

    # Create lists to store images
    to_generator = []
    to_reject = []

    if not face_locations:
        # No faces in the image, add to to_generator list
        to_generator.append(image_path)
    else:
        # Check if any face is in the top or bottom region
        is_face_in_top_bottom = any(top < img.height // 4 or bottom > 3 * img.height // 4 for (top, right, bottom, left) in face_locations)

        if is_face_in_top_bottom:
            # If any face is in the top or bottom region, add to to_reject list
            to_reject.append(image_path)
        else:
            # All faces are in the middle region or there are no faces, add to to_generator list
            to_generator.append(image_path)

    return to_generator, to_reject

# Function to process images in a folder
def process_images_in_folder(folder_path):
    to_generator = []
    to_reject = []

    # Iterate through all files in the folder
    for filename in os.listdir(folder_path):
        if filename.endswith(('.jpg', '.jpeg', '.png')):  # Check if the file is an image
            image_path = os.path.join(folder_path, filename)

            # Call the function to separate the image
            generator_list, reject_list = separate_image(image_path)

            # Add the results to the main lists
            to_generator.extend(generator_list)
            to_reject.extend(reject_list)

    return to_generator, to_reject

# Replace 'your_folder_path' with the path to your image folder
folder_path = sys.argv[1]

_, to_reject = process_images_in_folder(folder_path)

print(to_reject)


