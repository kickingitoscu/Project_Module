package de.fhws.fiw.instagram.peoplePitch;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransferInputInInputData
{
	PeoplePitchModel userData = new PeoplePitchModel(  );
	List<String> inputList;
	String regex = "::";

	public PeoplePitchModel transferInputDataInInputObject( List<String> inputList) throws IOException
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
			case "name": setName( setCompleteLine( splitLine ) );
				break;
			case "area": setArea( setCompleteLine( splitLine ));
				break;
			case "since": setSince( setCompleteLine( splitLine ) );
				break;
			case "firsttask": setFirstTaskInTheMorning( setCompleteLine( splitLine ) );
				break;
			case "favoritetask": setFavoriteTask( setCompleteLine( splitLine ) );
				break;
			case "outputpath": setOutputPath (setCompleteLine( splitLine ));
				break;
			case "position": setPosition(setCompleteLine( splitLine ));
				break;
			case "photopath": setPhotoPath( setCompleteLine( splitLine ) );
		}
	}

	private void setPosition( String position )
	{
		userData.setPosition( position );
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

	private void setName(String name){
		userData.setName( name );
	}

	private void setArea(String area){
		userData.setArea( area );
	}

	private void setSince(String since) {
		userData.setSince( since );
	}

	private void setFavoriteTask(String favoriteTask) {
		userData.setFavoriteTask( favoriteTask );
	}

	private void setFirstTaskInTheMorning(String firstTaskInTheMorning) {
		userData.setFirstTaskInTheMorning( firstTaskInTheMorning );
	}

	private void setOutputPath( String outputPath )
	{
		userData.setOutputPath( outputPath );
	}

	private void setPhotoPath(String photoPath) throws IOException
	{
		userData.setPhoto( ImageIO.read( new File( photoPath ) ) );
	}
}
