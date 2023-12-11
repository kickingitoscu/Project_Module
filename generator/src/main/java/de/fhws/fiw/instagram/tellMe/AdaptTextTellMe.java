package de.fhws.fiw.instagram.tellMe;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;

public class AdaptTextTellMe
{
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptTextTellMe(){
		initializeOutputObject();
		initializeInputObject();
	}

	public TextSpecificationOutput adaptPosition(String position){
		setFreeSpaceForTextInInputObjectFrontPagePosition();
		generateInputObjectForTextDeterminationPosition(position);
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptSpeech(String speech){
		setFreeSpaceForTextInInputObjectFrontPageSpeechBubble();
		generateInputObjectForTextDeterminationSpeech(speech);
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptName(String name){
		setFreeSpaceForTextInInputObjectFrontPageSpeechBubble();
		generateInputObjectForTextDeterminationName(name);
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptQuote(String quote){
		setFreeSpaceForTextInInputObjectQuotePage();
		generateInputObjectForTextDeterminationQuote( quote );
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptAfterStudy(String afterStudy){
		setFreeSpaceForTextInInputObjectDetailPage();
		generateInputObjectForTextDeterminationAfterStudy( afterStudy );
		identifyTextsize();
		return textSpecificationOutput;
	}

	public TextSpecificationOutput adaptHeadlineAfterStudy(String headlineAfterStudy){
		setFreeSpaceForTextInInputObjectDetailPage();
		generateInputObjectForTextDeterminationHeadlineAfterStudy( headlineAfterStudy );
		identifyTextsize();
		return textSpecificationOutput;
	}

	private void initializeInputObject( ) {
		textSpecificationInput = new TextSpecificationInput(  );
		setFontInInputObject();
		setFontStyleInInputObject();
	}

	private void setFreeSpaceForTextInInputObjectFrontPagePosition(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesTellMeFrontPage.lineLength );
	}

	private void setFreeSpaceForTextInInputObjectFrontPageSpeechBubble(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesTellMeFrontPage.textLengthBubble );
	}

	private void setFreeSpaceForTextInInputObjectQuotePage(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesTellMeQuotePage.freeSpaceForTextX );
	}

	private void setFreeSpaceForTextInInputObjectDetailPage(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesTellMeDetailPage.freeSpaceForTextX );
	}

	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	private void generateInputObjectForTextDeterminationPosition( String position )
	{
		setInputText( position );
		setMinMaxSizesPosition();
		setMaxLinesOfPosition();
	}

	private void generateInputObjectForTextDeterminationSpeech( String speech )
	{
		setInputText( speech );
		setMinMaxSizesSpeech();
		setMaxLinesOfSpeech();
	}

	private void generateInputObjectForTextDeterminationName( String name )
	{
		setInputText( name );
		setMinMaxSizesName();
		setMaxLinesOfName();
	}

	private void generateInputObjectForTextDeterminationQuote( String quote )
	{
		setInputText( quote );
		setMinMaxSizesQuote();
		setMaxLinesOfQuote();
	}

	private void generateInputObjectForTextDeterminationAfterStudy( String afterStudy )
	{
		setInputText( afterStudy );
		setMinMaxSizesAfterStudy();
		setMaxLinesOfAfterStudy();
	}

	private void generateInputObjectForTextDeterminationHeadlineAfterStudy( String headlineAfterStudy )
	{
		setInputText( headlineAfterStudy );
		setMinMaxSizesHeadlineAfterStudy();
		setMaxLinesOfHeadlineAfterStudy();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void identifyTextsize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution( );
	}

	private void setMaxLinesOfPosition(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeFrontPage.maxLinesOfPosition );
	}

	private void setMinMaxSizesPosition(){
		setMaxSize( ImageRulesTellMeFrontPage.maxTextSizePosition );
		setMinSize( ImageRulesTellMeFrontPage.minTextSizePosition );
	}

	private void setMinMaxSizesSpeech(){
		setMaxSize( ImageRulesTellMeFrontPage.maxTextSizeSpeech );
		setMinSize( ImageRulesTellMeFrontPage.minTextSizeSpeech );
	}

	private void setMinMaxSizesName(){
		setMaxSize( ImageRulesTellMeFrontPage.maxTextSizeSpeech );
		setMinSize( ImageRulesTellMeFrontPage.minTextSizeSpeech );
	}

	private void setMinMaxSizesQuote(){
		setMaxSize( ImageRulesTellMeQuotePage.maxTextSize );
		setMinSize( ImageRulesTellMeQuotePage.minTextSize );
	}

	private void setMinMaxSizesAfterStudy(){
		setMaxSize( ImageRulesTellMeDetailPage.maxTextSize );
		setMinSize( ImageRulesTellMeDetailPage.minTextSize );
	}

	private void setMinMaxSizesHeadlineAfterStudy(){
		setMaxSize( ImageRulesTellMeDetailPage.textSizeHeadline );
		setMinSize( ImageRulesTellMeDetailPage.textSizeHeadline );
	}

	private void setMaxLinesOfSpeech(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeFrontPage.maxLinesOfSpeech );
	}

	private void setMaxLinesOfName(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeFrontPage.maxLinesOfName );
	}

	private void setMaxLinesOfQuote(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeQuotePage.maxLinesOfText );
	}

	private void setMaxLinesOfHeadlineAfterStudy(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeDetailPage.maxLinesOfHeadlineAfterStudy);
	}

	private void setMaxLinesOfAfterStudy(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesTellMeDetailPage.maxLinesOfAfterStudy );
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
		textSpecificationInput.setFont( ImageRulesTellMeFrontPage.font );
	}

	private void setFontStyleInInputObject(){
		textSpecificationInput.setFontStyle( Font.BOLD );
	}
}
