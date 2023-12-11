package de.fhws.fiw.instagram.wayThroughStudies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransferInputInInputData
{
	ImportData userData = new ImportData(  );
	List<String> inputList;
	String regex = "::";


	public ImportData transferInputDataInInputObject( List<String> inputList) throws IOException
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
		case "headline": setHeadline( setCompleteLine( splitLine ) );
			break;
		case "image": setImage( setCompleteLine( splitLine ) );
			break;
		case "outputpath":setOutputPath( setCompleteLine( splitLine ) );
			break;
		case "footer":
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

	private void setHeadline(String headline){
		userData.setHeadline( headline );
	}

	private void setImage(String imagePath) throws IOException
	{
		userData.setImage( readImage( imagePath ) );
	}

	private Image readImage(String imagePath) throws IOException
	{
		return ImageIO.read( new File( imagePath ));
	}

	private void setOutputPath(String outputPath){
		userData.setOutputPath(outputPath );
	}
}
