package de.fhws.fiw.instagram.eventImage;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingEventImages
{
	private EventImageModel eventImageModel;

	public void generatePictures( EventImageModel data) throws IOException
	{
		this.eventImageModel = data;
		generateEventImages( );
	}

	private void generateEventImages( ) throws IOException{
		for (ComponentPropertiesConfig componentPropertiesConfig:ComponentPropertiesData.allComponentProperties)
		{
			for ( ColorConfig colorConfig : ColorData.allColorConfiguration )
			{
				BufferedImage image = new GeneratingEventImage( ).generatePicture( eventImageModel, colorConfig,componentPropertiesConfig );
				writeImage( image, colorConfig );
			}
		}
	}

	private void writeImage(BufferedImage image, ColorConfig colorConfig) throws IOException
	{
		String path = eventImageModel.getOutputPath( )+"EventImage_"+colorConfig.getName()+".jpg";
		ImageIO.write(image, "jpeg", new File(path));
	}
}
