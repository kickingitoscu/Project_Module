package de.fhws.fiw.instagram.peoplePitch;

import java.io.IOException;

public class PeoplePitch
{
	public static void main(String[] args) throws IOException
	{
		new GeneratingPicturesPeoplePitch().generatePictures( ReadImportData.getLoadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/UserInputPeoplePitch.txt" ));
	}
}
