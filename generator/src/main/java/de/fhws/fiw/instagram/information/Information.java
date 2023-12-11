package de.fhws.fiw.instagram.information;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Information
{
	public static void main(String[] args) throws IOException
	{
		InformationModel informationModel = new InformationLoader().loadByFileName( "/Users/vogel/Desktop/Bilder/User Input/Information.properties" );

		String outputPath = informationModel.getOutputPath()+"Information.jpg";
		BufferedImage informationImage = new GeneratingPictureInformation().generateImage( informationModel );
		ImageIO.write(informationImage, "jpeg", new File(outputPath));

		//new GeneratingPicturesCampusTour().generatePictures( ReadImportData.getloadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/CampusTour.txt" ));
	}
}
