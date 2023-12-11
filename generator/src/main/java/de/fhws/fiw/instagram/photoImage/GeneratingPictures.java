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
		for( ComponentPropertiesConfig textConfig: ComponentPropertiesData.textProperties){
			for( ColorConfig colorConfig: ColorData.allColorConfigurationsPhoto)
			{
				BufferedImage image = new GeneratingPicture.Builder().withImportData( data )
																	 .andColorConfig( colorConfig )
																	 .andComponentPropertiesConfig( textConfig )
																	 .generate();

				writeImage( image, colorConfig , textConfig);
			}
		}
	}

	private void writeImage(BufferedImage image, ColorConfig colorConfig, ComponentPropertiesConfig textConfig) throws IOException
	{
		String path = outputPath+textConfig.getName()+"_"+colorConfig.getName()+".jpg";
		ImageIO.write(image, "jpeg", new File(path));
	}

	private void setOutputPath(String path){
		outputPath = path;
	}
}
