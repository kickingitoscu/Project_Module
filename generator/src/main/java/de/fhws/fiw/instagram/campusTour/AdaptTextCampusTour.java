package de.fhws.fiw.instagram.campusTour;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;

public class AdaptTextCampusTour
{
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptTextCampusTour(){
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
		textSpecificationInput.setFreeSpaceForText( ImageRulesCampusTour.freeSpaceForText );
	}


	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	public TextSpecificationOutput adaptTitleConfig(String title){
		generateInputObjectTitleForTextDetermination(title);
		identifyTextSize();
		return textSpecificationOutput;
	}


	private void generateInputObjectTitleForTextDetermination( String title )
	{
		setInputText( title );
		setFontInInputObject();
		setFontStyleInInputObjectHeader();
		setMinMaxSizesTitle();
		setMaxLinesOfTextTitle();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void identifyTextSize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution(  );
	}

	private void setMaxLinesOfTextTitle(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesCampusTour.maxLinesOfTitle );
	}

	private void setMinMaxSizesTitle(){
		setMaxSize( ImageRulesCampusTour.maxTextSizeTitle);
		setMinSize( ImageRulesCampusTour.minTextSizeTitle );
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
		textSpecificationInput.setFont( ImageRulesCampusTour.font);
	}

	private void setFontStyleInInputObjectHeader(){
		textSpecificationInput.setFontStyle( Font.BOLD );
	}




}
