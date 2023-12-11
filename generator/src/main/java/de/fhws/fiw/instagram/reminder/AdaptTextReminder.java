package de.fhws.fiw.instagram.reminder;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;

public class AdaptTextReminder
{
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptTextReminder(){
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
		textSpecificationInput.setFreeSpaceForText( ImageRulesReminder.freeSpaceForHeaders );
	}


	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	public TextSpecificationOutput adaptReminderConfig(String reminder){
		generateInputObjectReminderForTextDetermination(reminder);
		identifyTextSize();
		return textSpecificationOutput;
	}


	private void generateInputObjectReminderForTextDetermination( String reminder )
	{
		setInputText( reminder );
		setFontInInputObject();
		setFontStyleInInputObjectHeaders();
		setMinMaxSizesReminder();
		setMaxLinesOfHeaders();
	}

	public TextSpecificationOutput adaptOkayConfig(String okay){
		generateInputObjectOkayForTextDetermination(okay);
		identifyTextSize();
		return textSpecificationOutput;
	}

	private void generateInputObjectOkayForTextDetermination( String okay )
	{
		setInputText( okay );
		setFontInInputObject();
		setFontStyleInInputObjectHeaders();
		setMinMaxSizesOkay();
		setMaxLinesOfHeaders();
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
		setFontStyleInInputObjectHeaders();
		setMinMaxSizesText();
		setMaxLinesOfText();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void setMinMaxSizesReminder(){
		setMaxSize( ImageRulesReminder.maxTextSizeReminder);
		setMinSize( ImageRulesReminder.minTextSizeReminder );
	}

	private void setMinMaxSizesOkay(){
		setMaxSize( ImageRulesReminder.maxTextSizeOkay);
		setMinSize( ImageRulesReminder.minTextSizeOkay );
	}

	private void setMinMaxSizesText(){
		setMaxSize( ImageRulesReminder.maxTextSizeText);
		setMinSize( ImageRulesReminder.minTextSizeText );
	}

	private void identifyTextSize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution(  );
	}

	private void setMaxLinesOfHeaders(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesReminder.maxLinesOfHeaders );
	}

	private void setMaxLinesOfText(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesReminder.maxLinesOfText );
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
		textSpecificationInput.setFont( ImageRulesReminder.font);
	}

	private void setFontStyleInInputObjectHeaders(){
		textSpecificationInput.setFontStyle( Font.BOLD );
	}
}
