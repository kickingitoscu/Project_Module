package de.fhws.fiw.instagram.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TextDistribution
{
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;
	private BufferedImage image;
	private Graphics2D g;

	public TextDistribution( TextSpecificationInput textSpecificationInput)
	{
		this.textSpecificationInput = textSpecificationInput;
		textSpecificationOutput = new TextSpecificationOutput(  );
	}

	public TextSpecificationOutput determineTextDistribution(  ){
		initializeOutputObjekt();
		initializeGraphic();
		splitTextIntoSeveralLines();
		return textSpecificationOutput;
	}

	private void initializeOutputObjekt (){
		setTargetFontSpecification();
	}

	private void initializeGraphic(){
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g =(Graphics2D) image.getGraphics();
	}

	private void splitTextIntoSeveralLines( )
	{
		int currentTextSize = textSpecificationInput.getMaxTextSize();
		setFontWithActualTextSize( currentTextSize);

		if (importDataHasOnlyOneWord( textSpecificationInput.getInputText() )){
			identifyTextSizeForOneWord();
		}

		else{
			List<String> splittedText = splitText( textSpecificationInput.getInputText( ) );

			while ( textSizeIsBiggerMinTextSize( currentTextSize ) && linesOfTextAreMoreThanMaxLinesOfText( splittedText ) )
			{
				currentTextSize--;
				setFontWithActualTextSize( currentTextSize );
				splittedText = splitText( textSpecificationInput.getInputText( ) );
			}

			textSpecificationOutput.setTextPerLine( getShortenedListIfNecessary( splittedText ) );
		}

	}

	private boolean importDataHasOnlyOneWord( String inputText ) {
		if (lineHasOnlyOneWord( inputText )){
			return true;
		}
		else
			return false;
	}

	private boolean lineHasOnlyOneWord(String inputText){
		return getNumberOfWordsInOneLine( inputText ) == 1;
	}

	private void identifyTextSizeForOneWord(){
		setTextSizeIfOneWordIsToLargeForOneLine( textSpecificationInput.getInputText() );
		textSpecificationOutput.setTextPerLine( new ArrayList (
			Collections.singleton( textSpecificationInput.getInputText( ) ) ));
	}

	private void setFontWithActualTextSize( int textSize )
	{
		g.setFont( new Font( textSpecificationOutput.getFont(), textSpecificationOutput.getFontStyle(), textSize ) );
	}

	private boolean linesOfTextAreMoreThanMaxLinesOfText( List<String> splittetText )
	{
		return splittetText.size( ) > textSpecificationInput.getMaxLinesOfText( );
	}

	private List<String> splitText(String text){
		int lineWidth;
		String currentLinePlusNextWord;

		String[] words = text.split( " " );
		int freeSpaceX= textSpecificationInput.getFreeSpaceForText();

		//Erstes Wort verarbeiten
		String currentLine=words[0];

		List<String> returnValue = new LinkedList<>(  );

		for( int i = 1;i<words.length;i++)
		{
			currentLinePlusNextWord = currentLine.isEmpty() ? words[i] : currentLine + " " + words[i]; // if line is empty, don't insert a space
			lineWidth = getTextWidth( currentLinePlusNextWord );

			if(lineWidth < freeSpaceX){
				currentLine = currentLinePlusNextWord;
			}
			else if(lineWidth == freeSpaceX){
				returnValue.add( currentLinePlusNextWord );
				currentLine="";
			}
			else if(lineWidth > freeSpaceX){
				if (getNumberOfWordsInOneLine( currentLine)==1){
					setTextSizeIfOneWordIsToLargeForOneLine( currentLine );
				}
				returnValue.add( currentLine );
				currentLine = words[ i ];
			}

			if(i == words.length-1){
				if (getNumberOfWordsInOneLine( currentLine)==1 && getTextWidth( currentLine )>freeSpaceX){
					setTextSizeIfOneWordIsToLargeForOneLine( currentLine );
				}
				returnValue.add( currentLine );
			}
		}

		textSpecificationOutput.setTextSize( g.getFont().getSize() );
		return returnValue;
	}

	private int getNumberOfWordsInOneLine(String line){
		return line.split( " " ).length;
	}

	private void setTextSizeIfOneWordIsToLargeForOneLine(String word){
		int currentTextSize = textSpecificationInput.getMaxTextSize();
		setFontWithActualTextSize( currentTextSize );

		while ( textWidthIsBiggerThanFreeSpace( word ) )
		{
			currentTextSize--;
			setFontWithActualTextSize( currentTextSize );
		}

		textSpecificationInput.setMaxTextSize( currentTextSize );
		textSpecificationOutput.setTextSize( currentTextSize );
		setFontWithActualTextSize( currentTextSize );
	}

	private List<String> getShortenedListIfNecessary(List<String> splittedText){

		if (linesOfTextAreMoreThanMaxLinesOfText( splittedText )){
			return getShortendList( splittedText );
		}
		else{
			return splittedText;
		}
	}

	private List<String> getShortendList(List<String> splittedText){
		/*ArrayList<String> shortedList = new ArrayList<>(  );

		for(int i = 0;i<textSpecificationInput.getMaxLinesOfText();i++){
			shortedList.add( splittedText.get( i ) );
		}
		return shortedList;*/
		return splittedText.stream().limit( textSpecificationInput.getMaxLinesOfText() ).collect( Collectors.toList() );
	}

	private boolean textWidthIsBiggerThanFreeSpace( String word )
	{
		return textSpecificationInput.getFreeSpaceForText( ) < getTextWidth( word );
	}

	private boolean textSizeIsBiggerMinTextSize(int size){
		return size > textSpecificationInput.getMinTextSize( );
	}

	private int getTextWidth(String text){
		return g.getFontMetrics().stringWidth( text );
	}

	private void setTargetFontSpecification(){
		setTargetFontInOutputObject();
		setTargetFontStyleInOutputObject();
	}

	private void setTargetFontInOutputObject(){
		textSpecificationOutput.setFont( textSpecificationInput.getFont() );
	}

	private void setTargetFontStyleInOutputObject(){
		textSpecificationOutput.setFontStyle( textSpecificationInput.getFontStyle() );
	}
}
