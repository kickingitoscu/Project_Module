package de.fhws.fiw.instagram.campusTour;

import java.io.IOException;

public class CampusTour
{
	public static void main(String[] args) throws IOException
	{
		//ImportData importData = ReadImportData.getloadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/CampusTour.txt");

		new GeneratingPicturesCampusTour().generatePictures( new CampusTourLoader().loadByFileName( "/Users/vogel/Desktop/Bilder/User Input/CampusTour.properties" ));
	}
}
