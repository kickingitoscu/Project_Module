package de.fhws.fiw.instagram.eventImage;

import java.io.IOException;

public class EventImage
{
	public static void main(String[] args) throws IOException
	{
		new GeneratingEventImages().generatePictures( ReadImportData.getLoadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/UserInputVeranstaltung.txt" ));
	}
}
