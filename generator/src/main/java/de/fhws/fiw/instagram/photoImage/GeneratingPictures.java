package de.fhws.fiw.instagram.photoImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPictures
{
	String outputPath;

	public void generatePictures( PhotoImageModel photoImageModel ) throws IOException
	{
		setOutputPath( photoImageModel.getOutputPath() );
		generateDifferentPictures( photoImageModel );
	}

	private void generateDifferentPictures( PhotoImageModel data)throws IOException{
		int i = 0;
		for( ComponentPropertiesConfig textConfig: ComponentPropertiesData.textProperties){
			for( ColorConfig colorConfig: ColorData.allColorConfigurationsPhoto)
			{
				BufferedImage image = new GeneratingPicture.Builder().withImportData( data )
																	 .andColorConfig( colorConfig )
																	 .andComponentPropertiesConfig( textConfig )
																	 .generate();
				String path = outputPath+String.valueOf(i)+".jpg";
				ImageIO.write(image, "jpeg", new File(path));
				i++;
			}
		}
	}

	private void setOutputPath(String path){
		outputPath = path;
	}
}
