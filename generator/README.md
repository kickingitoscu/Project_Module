# Instagram Generator

## Overview

This Instagram Generator gets information from a user (Headline, Subheadline, Image(path) and OutputPath) and
generates 204 different images for Instagram. These images have all the same size (1080x1080) and the same information.
The difference lies in the arrangement of the components textbox (textbox with header and subheader) and FIW THWS logo.

The Generator

- scales the imported image
- determines the text size of headline and subheadline
- determines the size of the textbox
- uses different cuttings for the image
- uses different colors for headline
- uses different colors for subheadline
- uses different colors for the logo
- uses different positions for the logo
- uses different colors for the textbox
- uses different positions for the textbox
- give the generated images specific names, depending on the configurations (see below)

## Notes for Installation

- The instagram generator is developed and tested with Java 8. The functions may be different in a higher version of Java.
- You may need to install the font INTER on your computer which is used as font for text in the generated images. You can find the different font versions in `src/main/resources/Font Inter`. For installation the font, you have to do a double click on each font file.
- You have to save the properties-file (UserInputPhoto.properties) and change the path of the import file in `src/main/java/de/fhws/fiw/instagram/photoImage/PhotoImage.java`
- `src/main/java/de/fhws/fiw/instagram/photoImage/PhotoImage.java` Is the main class which you have to run.
- Note: Depending on the computer you run the program, the generation takes more than two minutes

## Directory Overview

- `src/main/java/de/fhws/fiw/instagram/utils` In this directory you can find some files which are used for the different variants of Instagram images
- `src/main/java/de/fhws/fiw/instagram/photoImage` In this directory you can find the classes for generating the instagram image "PhotoImage"
- `src/main/resources/photos` In this directory you can find some photos for testing

## Layout Configurations

Codes for Layout Configuration - used for the names of the generated images (first three numbers)

Position Text:

- 0: top
- 1: down

Position Logo:

- 0: left
- 1: right

Cut of the imported image:

- 0: beginning
- 1: middle
- 2: end

You can find the different variants of Color combinations in
`src/main/java/de/fhws/fiw/instagram/photoImage/ComponentPropertiesData.java`

Example see below.

## Color Configurations

Codes for Color - used for the names of the generated images (last three numbers)

Color Background:

- 0: Black
- 1: White
- 2: Dark Grey
- 3: Green
- 4: Orange

Color Header:

- 0: Black
- 1: White
- 2: Dark Grey
- 3: Green
- 4: Orange

Color Subheader:

- 0: Black
- 1: White
- 2: Dark Grey
- 3: Green
- 4: Orange

You can find the different variants of Layout combinations in
`src/main/java/de/fhws/fiw/instagram/photoImage/ColorData.java`

The logo color is determined by the generator. The generator analyzes the colors of the imported image in the area the
logo will be positioned and calculates the brightness of this area. Depending on the brightness the logo color is set
to black, orange or white.

Example see below.

## Example: Naming of generated images

Example: `Photo_1_0_2_1_3_2.jpg`

### Layout Configurations

**Photo_1_0_2**\_1_3_2

- Photo is the name that every generated image gets
- 1: the textbox with headline and subheadline is positioned at the bottom of the image
- 0: the logo is positioned on the top left side of the image
- 2: the imported image is cut off towards the end of the image

### Color Configuration

Photo*1_0_2***1_3_2**

- 1: the textbox is colored white
- 3: the headline is colored green
- 2: the subheadline is colored dark grey
