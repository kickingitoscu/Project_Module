package de.fhws.fiw.instagram.photoImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransferInputInInputData
{
	PhotoImageModel userData = new PhotoImageModel(  );
	List<String> inputList;
	String regex = "::";

	public PhotoImageModel transferInputDataInInputObject( List<String> inputList) throws IOException
	{
		this.inputList = inputList;

		handleEachLineOfInput();

		return userData;
	}

	private void handleEachLineOfInput() throws IOException{
		for(String item: inputList){
			classifyInformationOfLine( item );
		}
	}

	private void classifyInformationOfLine(String inputLine) throws IOException
	{
		String [] split = inputLine.split( regex );

		switch ( split[0].toLowerCase() ){
			case "header": setHeader( setCompleteLine( split ) );
				break;
			case "subheader":setSubheader( setCompleteLine( split ));
				break;
			case "image": readInputPicture( setCompleteLine( split ) );
				break;
			case "outputpath":setOutputPath( setCompleteLine( split ) );
		}
	}

	private String setCompleteLine(String[] splittedLine){
		String line = splittedLine[ 1 ];

		if (splittedLine.length>2){
			for (int i = 2; i<splittedLine.length; i++){
				line = line + regex + splittedLine[i];
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

	private void readInputPicture(String imagePath) throws IOException {
		userData.setImportedImage(ImageIO.read(new File( imagePath) ) ) ;
	}

	private void setOutputPath(String outputPath){
		userData.setOutputPath(outputPath );
	}
}
