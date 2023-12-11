package de.fhws.fiw.instagram.tellMe;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneratingPictureDetailPage
{
	private BufferedImage image;
	private Graphics2D g;
	private TellMeModel tellMeModel;
	private TextSpecificationOutput textSpecificationOutputAdditionalQuestion, textSpecificationOutputHeadlineAdditionalQuestion;
	private int yCoordinate;


	public BufferedImage generateImage( TellMeModel tellMeModel )
	{
		setImportData( tellMeModel );
		configureGraphics( );
		placeBackgroundColor( );
		placeHeadlineAboutPerson();
		placeLineForHeadlineAboutPerson();
		adaptTextAdditionalQuestion();
		placeAboutPerson();
		placeHeadlineAdditionalQuestion();
		placeLineFoHeadlineAdditionalQuestion();
		placeAdditionalQuestion();

		return image;
	}


	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( TellMeModel tellMeModel ) {
		this.tellMeModel = tellMeModel;
	}

	private void placeBackgroundColor() {
		g.setColor( FHWS_Color.lightOrange );
		g.fillRect( 0,0,ImageRules.pictureWidth, ImageRules.pictureHeight );
	}

	private void placeHeadlineAboutPerson(){
		setFontConfigurationHeader();
		g.drawString( getHeadlineTextAboutPerson(), ImageRulesTellMeDetailPage.freeSpaceToStartTextX, ImageRulesTellMeDetailPage.freeSpaceToStartTextY );
	}

	private String getHeadlineTextAboutPerson(){
		return "Über " + tellMeModel.getName() + " ...";
	}

	private void placeLineForHeadlineAboutPerson(){
		setColorLineForHeadline();
		g.fillRect( ImageRulesTellMeDetailPage.freeSpaceToStartTextX, getYCoordinateForLineHeadlineAboutPerson(),
			getTextWidth( getHeadlineTextAboutPerson() ), getHeightOfHeadlineLine() );
	}

	private int getYCoordinateForLineHeadlineAboutPerson(){
		return ImageRulesTellMeDetailPage.freeSpaceToStartTextY + ImageRulesTellMeDetailPage.textSizeHeadline/3;
	}

	private void placeAboutPerson(){
		setFontText();
		setFontColorText();
		drawAboutPersonOnImage();
	}

	private void drawAboutPersonOnImage(){
		yCoordinate = getYCoordinateFirstLineAboutPerson();

		for ( int i = 0; i < tellMeModel.getAboutPerson().size(); i++){
			yCoordinate = yCoordinate + getSpaceForLineBreak();
			g.fillOval( ImageRulesTellMeDetailPage.freeSpaceToStartTextX, yCoordinate-(textSpecificationOutputAdditionalQuestion.getTextSize()/2), 15,15 );
			g.drawString(  tellMeModel.getAboutPerson().get( i ),
				ImageRulesTellMeDetailPage.freeSpaceToStartTextX+30, yCoordinate );
		}
	}

	private int getYCoordinateFirstLineAboutPerson(){
		return getYCoordinateForLineHeadlineAboutPerson() + textSpecificationOutputAdditionalQuestion.getTextSize();
	}

	private int getSpaceForLineBreak(){
		return textSpecificationOutputAdditionalQuestion.getTextSize() + (textSpecificationOutputAdditionalQuestion.getTextSize()/4);
	}

	private void adaptTextAdditionalQuestion(){
		setTextSpecificationOutputAdditionalQuestion();
	}


	private void setTextSpecificationOutputAdditionalQuestion(){
		textSpecificationOutputAdditionalQuestion = new AdaptTextTellMe().adaptAfterStudy( tellMeModel.getAdditionalQuestionAnswer( ) );
	}

	private void placeHeadlineAdditionalQuestion() {
		setFontConfigurationHeader();
		setTextSpecificationOutputHeadlineAdditionalQuestion();
		drawHeadlineAdditionalQuestionOnImage();
	}

	private void drawHeadlineAdditionalQuestionOnImage(){
		yCoordinate = yCoordinate + 100;

		for ( int i = 0; i < textSpecificationOutputHeadlineAdditionalQuestion.getTextPerLine().size(); i++){
			yCoordinate = yCoordinate + textSpecificationOutputHeadlineAdditionalQuestion.getTextSize();
			g.drawString( textSpecificationOutputHeadlineAdditionalQuestion.getTextPerLine().get( i ),
				getXCoordinateRightAlinedLine( textSpecificationOutputHeadlineAdditionalQuestion.getTextPerLine().get( i ) ),
				yCoordinate );
		}
	}

	private void placeLineFoHeadlineAdditionalQuestion(){
		setColorLineForHeadline();
		drawLineForHeadlineAdditionalQuestion();
	}

	private void drawLineForHeadlineAdditionalQuestion(){
		getAdditionalSpaceBetweenHeadlineAndLine();

		g.fillRect( getXCoordinateRightAlinedLine( ) , yCoordinate,
			getLengthOfLongestLineOfHeadlineAdditionalQuestion(), getHeightOfHeadlineLine());
	}

	private void getAdditionalSpaceBetweenHeadlineAndLine(){
		yCoordinate = yCoordinate + ImageRulesTellMeDetailPage.textSizeHeadline/3;
	}


	private int getLengthOfLongestLineOfHeadlineAdditionalQuestion(){
		int longestLineLength = 0;
		for( int i = 0; i < textSpecificationOutputHeadlineAdditionalQuestion.getTextPerLine().size(); i++){
			if ( actualLineIsLongerThanLinesBefore( longestLineLength, i ) ){
				longestLineLength = getTextWidth( getActualLineHeadlineAdditionalQuestion( i ) );
			}
		}
		return longestLineLength;
	}

	private boolean actualLineIsLongerThanLinesBefore( int longestLine, int i )
	{
		return longestLine < getTextWidth( getActualLineHeadlineAdditionalQuestion( i ) );
	}

	private String getActualLineHeadlineAdditionalQuestion(int i){
		return textSpecificationOutputHeadlineAdditionalQuestion.getTextPerLine().get( i );
	}

	private int getHeightOfHeadlineLine(){
		return 8;
	}

	private void setTextSpecificationOutputHeadlineAdditionalQuestion(){
		textSpecificationOutputHeadlineAdditionalQuestion = new AdaptTextTellMe().adaptHeadlineAfterStudy(
			tellMeModel.getAdditionalQuestion( ) );
	}

	private void placeAdditionalQuestion(){
		setFontText();
		setFontColorText();
		drawAdditionalQuestionOnImage();
	}

	private void drawAdditionalQuestionOnImage(){
		getAdditionalSpaceBetweenLineAndAdditionalQuestion();

		for ( int i = 0; i < textSpecificationOutputAdditionalQuestion.getTextPerLine().size(); i++){
			yCoordinate = yCoordinate + getSpaceForLineBreak();
			g.drawString( textSpecificationOutputAdditionalQuestion.getTextPerLine().get( i ),
				getXCoordinateRightAlinedLine( textSpecificationOutputAdditionalQuestion.getTextPerLine().get( i ) ), yCoordinate );
		}
	}

	private void getAdditionalSpaceBetweenLineAndAdditionalQuestion(){
		yCoordinate = yCoordinate + textSpecificationOutputAdditionalQuestion.getTextSize();
	}

	private int getXCoordinateRightAlinedLine(String line){
		return ImageRules.pictureWidth - ImageRulesTellMeDetailPage.freeSpaceToStartTextX - getTextWidth( line );
	}

	private int getXCoordinateRightAlinedLine(){
		return ImageRules.pictureWidth - ImageRulesTellMeDetailPage.freeSpaceToStartTextX - getLengthOfLongestLineOfHeadlineAdditionalQuestion();
	}

	private void setFontConfigurationHeader(){
		setFontColorText();
		g.setFont( new Font( ImageRulesTellMeDetailPage.font, Font.BOLD, ImageRulesTellMeDetailPage.textSizeHeadline ) );
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}

	private void setColorLineForHeadline(){
		g.setColor( FHWS_Color.orange );
	}

	private void setFontColorText(){
		g.setColor( Color.BLACK );
	}

	private void setFontText(){
		g.setFont( new Font( ImageRulesTellMeDetailPage.font, Font.PLAIN, textSpecificationOutputAdditionalQuestion.getTextSize() ) );
	}

}

