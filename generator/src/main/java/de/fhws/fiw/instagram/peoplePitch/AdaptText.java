package de.fhws.fiw.instagram.peoplePitch;

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

	public TextSpecificationOutput adaptFavoriteTask(String favoriteTask){
		setFreeSpaceForTextInInputObjectDetailPage();
		generateInputObjectFavoriteTaskForTextDetermination(favoriteTask);
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptFirstTaskIntTheMorning(String firstTaskInTheMorning){
		setFreeSpaceForTextInInputObjectDetailPage();
		generateInputObjectFirstTaskInTheMorningForTextDetermination( firstTaskInTheMorning);
		identifyTextsize();

		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptPeoplePitch(String peoplePitch){
		setFreeSpaceForTextInInputObjectFrontPagePeoplePitch();
		generateInputObjectPeoplePitchForTextDetermination(peoplePitch);
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptNameAndArea(String nameAndArea){
		setFreeSpaceForTextInInputObjectFrontPageNameAndArea();
		generateInputObjectNameAndAreaForTextDetermination(nameAndArea);
		identifyTextsize();

		return textSpecificationOutput;
	}

	private void initializeInputObject( ) {
		textSpecificationInput = new TextSpecificationInput(  );
		setFontInInputObject();
		setFontStyleInInputObject();
	}

	private void setFreeSpaceForTextInInputObjectDetailPage(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesPeoplePitchDetailPage.freeSpaceXForText );
	}

	private void setFreeSpaceForTextInInputObjectFrontPageNameAndArea(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesPeoplePitchFrontPage.freeSpaceXForText - 20);
	}

	private void setFreeSpaceForTextInInputObjectFrontPagePeoplePitch(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesPeoplePitchFrontPage.freeSpaceXForText);
	}

	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	private void generateInputObjectFavoriteTaskForTextDetermination( String favoriteTask )
	{
		setInputText( favoriteTask );
		setMinMaxSizesDetailPage();
		setMaxLinesOfTextFavoriteTask();
	}

	private void generateInputObjectFirstTaskInTheMorningForTextDetermination( String firstTaskInTheMorning )
	{
		setInputText( firstTaskInTheMorning );
		setMinMaxSizesDetailPage();
		setMaxLinesOfTextFirstTaskInTheMorning();
	}

	private void generateInputObjectNameAndAreaForTextDetermination( String nameAndArea )
	{
		setInputText( nameAndArea );
		setMinMaxSizesNameAndArea();
		setMaxLinesOfTextNameAndArea();
	}

	private void generateInputObjectPeoplePitchForTextDetermination( String peoplePitch )
	{
		setInputText( peoplePitch );
		setMinMaxSizesPeoplePitch();
		setMaxLinesOfTextPeoplePitch();
	}


	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void identifyTextsize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution( );
	}

	private void setMaxLinesOfTextFavoriteTask(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPeoplePitchDetailPage.maxLinesOfFavoriteTask );
	}

	private void setMaxLinesOfTextFirstTaskInTheMorning(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPeoplePitchDetailPage.maxLinesOfFirstTaskInTheMorning);
	}

	private void setMaxLinesOfTextNameAndArea(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPeoplePitchFrontPage.maxLinesOfNameAndArea);
	}

	private void setMaxLinesOfTextPeoplePitch(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPeoplePitchFrontPage.maxLinesOfPeoplePitch);
	}

	private void setMinMaxSizesDetailPage(){
		setMaxSize( ImageRulesPeoplePitchDetailPage.textSize );
		setMinSize( ImageRulesPeoplePitchDetailPage.textSize );
	}

	private void setMinMaxSizesNameAndArea(){
		setMaxSize( ImageRulesPeoplePitchFrontPage.textSizeNameAndArea );
		setMinSize( ImageRulesPeoplePitchFrontPage.textSizeNameAndArea );
	}

	private void setMinMaxSizesPeoplePitch(){
		setMaxSize( ImageRulesPeoplePitchFrontPage.maxTextSizePeoplePitch );
		setMinSize( ImageRulesPeoplePitchFrontPage.minTextSizePeoplePitch );
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
		textSpecificationInput.setFont( ImageRulesPeoplePitchDetailPage.font );
	}

	private void setFontStyleInInputObject(){
		textSpecificationInput.setFontStyle( Font.BOLD );
	}

}
