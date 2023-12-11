package de.fhws.fiw.instagram.fiwRecommends;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FIWRecommends
{
	public static void main(String[] args) throws IOException
	{
		//FiwRecommendsModel fiwRecommends = FiwRecommendsModel.load( "/Users/vogel/Desktop/Bilder/User Input/FIWRecommendsV2.properties" );


		FiwRecommendsModel importData = new FiwRecommendsLoader().loadByFileName( "/Users/vogel/Desktop/Bilder/User Input/FIWRecommends.properties" );
		//ImportData importData = ReadImportData.getLoadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/FIWRecommends.txt");

		String outputPath = importData.getOutputPath()+"FIWRecommends.jpg";
		BufferedImage informationImage = new GeneratingPictureFIWRecommends().generateImage( importData );
		ImageIO.write(informationImage, "jpeg", new File(outputPath));

		//new GeneratingPicturesCampusTour().generatePictures( ReadImportData.getloadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/CampusTour.txt" ));
	}
}
