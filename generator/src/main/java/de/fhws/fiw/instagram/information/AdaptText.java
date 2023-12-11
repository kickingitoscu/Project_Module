package de.fhws.fiw.instagram.information;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;

public class AdaptText
{
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptText(){
		initializeOutputObject();
		initializeInputObject();
	}

	private void initializeInputObject( )
	{
		textSpecificationInput = new TextSpecificationInput(  );
		setFreeSpaceForTextInInputObject();
	}

	private void setFreeSpaceForTextInInputObject()
	{
		textSpecificationInput.setFreeSpaceForText( ImageRulesInformation.freeSpaceForText );
	}

	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	public TextSpecificationOutput adaptTextConfig(String text){
		generateInputObjectTextForTextDetermination(text);
		identifyTextSize();
		return textSpecificationOutput;
	}

	private void generateInputObjectTextForTextDetermination( String text )
	{
		setInputText( text );
		setFontInInputObject();
		setFontStyleInInputObjectText();
		setMinMaxSizesText();
		setMaxLinesOfText();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void setMinMaxSizesText(){
		setMaxSize( ImageRulesInformation.maxTextSizeText);
		setMinSize( ImageRulesInformation.minTextSizeText );
	}

	private void identifyTextSize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution(  );
	}

	private void setMaxLinesOfText(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesInformation.maxLinesOfText );
	}

	private void setMinSize( int minSize )
	{
		textSpecificationInput.setMinTextSize( minSize );
	}

	private void setMaxSize( int maxSize )
	{
		textSpecificationInput.setMaxTextSize( maxSize );
	}

	private void setFontInInputObject(){
		textSpecificationInput.setFont( ImageRulesInformation.font);
	}

	private void setFontStyleInInputObjectText(){
		textSpecificationInput.setFontStyle( Font.BOLD );
	}
}
