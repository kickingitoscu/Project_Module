package de.fhws.fiw.instagram.reminder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransferInputInInputData
{
	ReminderModel userData = new ReminderModel(  );
	List<String> inputList;
	String regex = "::";

	public ReminderModel transferInputDataInInputObject( List<String> inputList) throws IOException
	{
		this.inputList = inputList;

		handleEachLineOfInput();

		return userData;
	}


	private void handleEachLineOfInput() throws IOException
	{
		for(String item: inputList){
			classifyInformationOfLine( item );
		}
	}

	private void classifyInformationOfLine(String inputLine) throws IOException
	{
		String [] splitLine = inputLine.split( regex );

		switch ( splitLine[0].toLowerCase() ){
			case "text": setText( setCompleteLine( splitLine ) );
				break;
			case "photo": setPhoto( setCompleteLine( splitLine ) );
				break;
			case "outputpath":setOutputPath( setCompleteLine( splitLine ) );
		}
	}


	private String setCompleteLine(String[] splitLine){
		String line = splitLine[ 1 ];

		if (splitLine.length>2){
			for (int i = 2; i<splitLine.length; i++){
				line = line + regex + splitLine[i];
			}
		}

		return line;
	}

	private void setText(String text){
		userData.setText( text );
	}

	private void setPhoto(String photoPath) throws IOException
	{
		userData.setPhoto( readPhoto( photoPath ) );
	}

	private Image readPhoto(String photoPath) throws IOException
	{
		return ImageIO.read( new File( photoPath ));
	}

	private void setOutputPath(String outputPath){
		userData.setOutputPath(outputPath );
	}

}
