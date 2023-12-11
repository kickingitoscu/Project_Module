package de.fhws.fiw.instagram.tellMe;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GeneratingPictureQuotePage
{
	private BufferedImage image;
	private Graphics2D g;
	private TellMeModel tellMeModel;
	private TextSpecificationOutput textSpecificationOutputQuote;
	private int leftQuotationMarkX, leftQuotationMarkY, rightQuotationMarkX, rightQuotationMarkY;


	public BufferedImage generateImage( TellMeModel tellMeModel ) throws IOException
	{
		setImportData( tellMeModel );
		configureGraphics( );
		placeBackgroundColor( );
		placeQuote();

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

	private void placeQuote() throws IOException
	{
		setQuoteTextConfiguration();
		setFontConfigurationQuote();
		placeQuotationMarks();
		drawQuoteInImage();
	}

	private void setQuoteTextConfiguration(){
		textSpecificationOutputQuote = new AdaptTextTellMe().adaptQuote( tellMeModel.getQuote( ) );
	}

	private void drawQuoteInImage(){
		int yCooridnate = getYCoordinateForFirstQuoteLine();

		for (int i = 0; i < textSpecificationOutputQuote.getTextPerLine().size(); i++){
			yCooridnate = yCooridnate + getSpaceForLineBreak();
			g.drawString( textSpecificationOutputQuote.getTextPerLine().get( i ),
				getXCoordinateQuoteCentered(textSpecificationOutputQuote.getTextPerLine().get( i )), yCooridnate );
		}
	}

	private int getYCoordinateForFirstQuoteLine(){
		return ImageRulesTellMeQuotePage.middlePointPicture - ( getNeededHeightForText()/2);
	}

	private int getNumberOfLinesOfText(){
		return textSpecificationOutputQuote.getTextPerLine().size();
	}

	private int getNeededHeightForText(){
		return getSpaceForLineBreak() * getNumberOfLinesOfText();
	}

	private int getSpaceForLineBreak(){
		return textSpecificationOutputQuote.getTextSize( );
	}

	private int getXCoordinateQuoteCentered(String line){
		return getXCoordinateMiddlePointForPositioning() - (getTextWidth( line )/2);
	}

	private int getXCoordinateMiddlePointForPositioning(){
		return ImageRulesTellMeQuotePage.middlePointPicture;
	}

	private void placeQuotationMarks() throws IOException
	{
		determineCoordinatesForLeftQuotationMark();
		drawQuotationMarkLeft();
		determineCoordinatesForRightQuotationMark();
		draqQuotationMarkRight();
	}

	private void drawQuotationMarkLeft() throws IOException
	{
		Image quotationMarkLeft =  ImageIO.read( this.getClass().getResourceAsStream( "/tellMe/AnfuehrungszeichenLinks.png" ) );
		g.drawImage( quotationMarkLeft, leftQuotationMarkX, leftQuotationMarkY,null  );
	}

	private void determineCoordinatesForLeftQuotationMark(){
		leftQuotationMarkY = getYCoordinateForFirstQuoteLine() - 100;
		leftQuotationMarkX = getXCoordinateQuoteCentered( getFirstLineOfText() ) - 120;
	}

	private void draqQuotationMarkRight() throws IOException{
		Image quotationMarkRight = ImageIO.read( this.getClass().getResourceAsStream( "/tellMe/AnfuehrungszeichenRechts.png" ) );
		g.drawImage( quotationMarkRight, rightQuotationMarkX, rightQuotationMarkY, null );
	}

	private void determineCoordinatesForRightQuotationMark(){
		rightQuotationMarkX = getXCoordinateQuoteCentered( getLastLineOfText() ) + getTextWidth(getLastLineOfText() ) - 120;
		rightQuotationMarkY = getYCoordinateForFirstQuoteLine() + getNeededHeightForText() - getSpaceForLineBreak();
	}

	private String getLastLineOfText(){
		return textSpecificationOutputQuote.getTextPerLine().get( getNumberOfLinesOfText()-1 );
	}

	private String getFirstLineOfText(){
		return textSpecificationOutputQuote.getTextPerLine().get( 0 );
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}

	private void setFontConfigurationQuote(){
		g.setFont( new Font( ImageRulesTellMeQuotePage.font, Font.BOLD,textSpecificationOutputQuote.getTextSize() ) );
		g.setColor( Color.black );
	}
}

