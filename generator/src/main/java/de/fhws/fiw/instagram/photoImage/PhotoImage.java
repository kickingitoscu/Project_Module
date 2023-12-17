package de.fhws.fiw.instagram.photoImage;


import java.io.IOException;

public class PhotoImage
{
	public static void main(String[] args) throws IOException
	{
		new GeneratingPictures().generatePictures(new PhotoImageLoader().loadByFileName(args[0]));	
	}
}
