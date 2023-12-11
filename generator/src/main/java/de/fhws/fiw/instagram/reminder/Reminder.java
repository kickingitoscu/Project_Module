package de.fhws.fiw.instagram.reminder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Reminder
{
	public static void main(String[] args) throws IOException
	{
		ReminderModel reminderModel = ReadImportData.getLoadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/Reminder.txt");

		String outputPath = reminderModel.getOutputPath()+"Reminder.jpg";
		BufferedImage reminderImage = new GeneratingPictureReminder().generateImage( reminderModel );
		ImageIO.write(reminderImage, "jpeg", new File(outputPath));

		//new GeneratingPicturesCampusTour().generatePictures( ReadImportData.getloadedUserImportData( "/Users/vogel/Desktop/Bilder/User Input/CampusTour.txt" ));
	}
}
