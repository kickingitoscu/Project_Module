package de.fhws.fiw.instagram.tellMe;

import java.io.IOException;

public class TellMe
{
	public static void main(String[] args) throws IOException
	{
		new GeneratingPicturesTellMe().generatePictures( ReadImportData.getLoadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/UserInputTellMe.txt" ));
	}
}
