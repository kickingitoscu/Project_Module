package de.fhws.fiw.instagram.eventImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferInputInInputData
{
	EventImageModel userData = new EventImageModel(  );
	List<String> inputList;
	String regex = "::";
	List<String> contentList = new ArrayList<>(  );

	public EventImageModel transferInputDataInInputObject( List<String> inputList) throws IOException
	{
		this.inputList = inputList;

		handleEachLineOfInput();
		setCollectedContentListInImportObject();

		return userData;
	}


	private void handleEachLineOfInput() {
		for(String item: inputList){
			classifyInformationOfLine( item );
		}
	}

	private void classifyInformationOfLine(String inputLine)
	{
		String [] splitLine = inputLine.split( regex );


		switch ( splitLine[0].toLowerCase() ){
			case "header": setHeader( setCompleteLine( splitLine ) );
				break;
			case "subheader": setSubheader( setCompleteLine( splitLine ));
				break;
			case "host": setHost( setCompleteLine( splitLine ) );
				break;
			case "location": setLocation( setCompleteLine( splitLine ) );
				break;
			case "time": setTime( setCompleteLine( splitLine ) );
				break;
			case "content": setContent( setCompleteLine( splitLine ) );
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

	private void setHeader(String header){
		userData.setHeader( header );
	}

	private void setSubheader(String subheader){
		userData.setSubheader( subheader );
	}

	private void setHost(String host) {
		userData.setHost( host );
	}

	private void setTime(String time) {
		userData.setTime( time );
	}

	private void setLocation(String location) {
		userData.setLocation( location );
	}

	private void setContent(String content) {
		contentList.add( content );
	}

	private void setCollectedContentListInImportObject( )
	{
		userData.setContent( contentList );
	}

	private void setOutputPath(String outputPath){
		userData.setOutputPath(outputPath );
	}
}
