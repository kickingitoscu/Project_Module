package de.fhws.fiw.instagram.tellMe;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferInputInInputData
{
	TellMeModel userData = new TellMeModel(  );
	List<String> inputList;
	String regex = "::";
	List<String> aboutPerson = new ArrayList<>(  );

	public TellMeModel transferInputDataInInputObject( List<String> inputList) throws IOException
	{
		this.inputList = inputList;

		handleEachLineOfInput();
		setListAboutPersonInImportData();

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
			case "speech": setSpeech (setCompleteLine( splitLine ));
				break;
			case "name": setName( setCompleteLine( splitLine ) );
				break;
			case "position": setPosition( setCompleteLine( splitLine ));
				break;
			case "quote": setQuote( setCompleteLine( splitLine ) );
				break;
			case "aboutperson": setAboutPerson( setCompleteLine( splitLine ) );
				break;
			case "outputpath": setOutputPath (setCompleteLine( splitLine ));
				break;
			case "photopath": setPhotoPath( setCompleteLine( splitLine ) );
				break;
			case "additionalquestionanswer": setAdditionalQuestionAnswer( setCompleteLine( splitLine ) );
				break;
			case "additionalquestion": setAdditionalQuestion( setCompleteLine( splitLine ) );
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

	private void setPosition( String position )
	{
		userData.setPosition( position );
	}

	private void setName(String name){
		userData.setName( name );
	}

	private void setSpeech(String speech){
		userData.setSpeech( getSpeech(speech) );
	}

	private String getSpeech(String speech){
		if( speech.equalsIgnoreCase( "du" )){
			return "ERZÄHL MAL,";
		}
		else{
			return "ERZÄHLEN SIE MAL,";
		}
	}

	private void setQuote(String quote) {
		userData.setQuote( quote );
	}

	private void setAboutPerson(String aboutPerson) {
		this.aboutPerson.add( aboutPerson );
	}

	private void setOutputPath( String outputPath )
	{
		userData.setOutputPath( outputPath );
	}

	private void setPhotoPath(String photoPath) throws IOException
	{
		userData.setPhoto( ImageIO.read( new File( photoPath ) ) );
	}

	private void setAdditionalQuestionAnswer(String additionalQuestionAnswer){
		userData.setAdditionalQuestionAnswer( additionalQuestionAnswer );
	}

	private void setListAboutPersonInImportData(){
		userData.setAboutPerson( aboutPerson );
	}

	private void setAdditionalQuestion(String additionalQuestion){
		userData.setAdditionalQuestion( additionalQuestion );
	}
}
