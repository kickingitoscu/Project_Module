package de.fhws.fiw.instagram.tellMe;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

public class GeneratingPictureFrontPage
{
	private BufferedImage image;
	private Graphics2D g;
	private TellMeModel tellMeModel;
	private TextSpecificationOutput textSpecificationOutputPosition, textSpecificationOutputSpeech, textSpecificationOutputName;


	public BufferedImage generateImage( TellMeModel tellMeModel ) throws IOException
	{
		setImportData( tellMeModel );
		configureGraphics( );
		placeBackgroundColor( );
		placeLine();
		placePhoto();
		placeSpeechBubble();
		placePosition();

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

	private void placeLine(){
		g.setColor( FHWS_Color.orange );
		g.fillRect( ImageRulesTellMeFrontPage.xCoordinateLine, ImageRulesTellMeFrontPage.yCoordinateLine,
			ImageRulesTellMeFrontPage.lineLength,10 );
	}

	private void placePhoto()
	{
		scalePhoto();
		drawPhotoOnImage();
	}

	private void scalePhoto(){
		double scale = (double ) ImageRulesTellMeFrontPage.pictureWidth / (double) tellMeModel.getPhoto().getWidth( null ) ;

		tellMeModel.setPhoto( tellMeModel.getPhoto().getScaledInstance(( int ) ( tellMeModel.getPhoto().getWidth( null )* scale), (int) (
			tellMeModel.getPhoto().getHeight( null )*scale), Image.SCALE_SMOOTH));
	}

	private void drawPhotoOnImage(){
		g.drawImage( tellMeModel.getPhoto(), 0 , 0, null);
	}

	private void placeSpeechBubble() throws IOException
	{
		drawSpeechBubbleOnImage();
		placeSpeechInSpeechBubble();
	}

	private void drawSpeechBubbleOnImage() throws IOException
	{
		g.drawImage( ImageIO.read( this.getClass().getResourceAsStream( "/tellMe/Sprechblase.png" ) ) ,
			ImageRulesTellMeFrontPage.xCoordinateSpeechBubble, ImageRulesTellMeFrontPage.yCoordinateSpeechBubble,null );
	}

	private void placeSpeechInSpeechBubble(){
		setFontColorText();
		setSpeechTextConfiguration();
		setNameTextConfiguration();
		setFontInSpeechBubble();
		drawSpeechOnImage();
		drawNameInSpeechBubble();
	}

	private void setSpeechTextConfiguration(){
		textSpecificationOutputSpeech = new AdaptTextTellMe().adaptSpeech( tellMeModel.getSpeech( ) );
	}

	private void setNameTextConfiguration(){
		textSpecificationOutputName = new AdaptTextTellMe().adaptName( tellMeModel.getName().toUpperCase( Locale.ROOT ) );
	}

	private void setFontInSpeechBubble(){
		if( textSizeNameIsSmalerThanTextSizeSpeech( ) ){
			g.setFont( new Font( ImageRulesTellMeFrontPage.font, Font.BOLD,textSpecificationOutputName.getTextSize() ) );
		}
		else{
			g.setFont( new Font( ImageRulesTellMeFrontPage.font, Font.BOLD,textSpecificationOutputSpeech.getTextSize() ) );
		}
	}

	private void drawSpeechOnImage(){
		g.drawString( textSpecificationOutputSpeech.getTextPerLine().get( 0 ),
			getXCoordinateSpeechCentered( textSpecificationOutputSpeech.getTextPerLine().get( 0 ) ),
			getYCoordinateSpeech() );
	}

	private void drawNameInSpeechBubble(){
		int yCoordinate = getYCoordinateSpeech();
		int i = 0;

		do
		{
			yCoordinate = yCoordinate + getSpaceForLineBreakInSpeechBubble();

			if( isLastLine( i ) ){
				g.drawString( getName( i ) + " ...",
					getXCoordinateSpeechCentered( getName( i ) + " ..." ), yCoordinate );
			}
			else{
				g.drawString( getName( i ), getXCoordinateSpeechCentered( getName( i )), yCoordinate );
			}
			i++;

		} while (i<textSpecificationOutputName.getTextPerLine().size());

	}

	private boolean isLastLine( int i )
	{
		return i == textSpecificationOutputName.getTextPerLine( ).size( ) - 1;
	}

	private int getSpaceForLineBreakInSpeechBubble(){
		return 60;
	}

	private String getName(int i){
		return textSpecificationOutputName.getTextPerLine().get( i );
	}

	private int getYCoordinateSpeech(){
		if ( nameHasMoreThanOneLine( ) ){
			return ImageRulesTellMeFrontPage.yCoordinateSpeech - 20;
		}
		else {
			return ImageRulesTellMeFrontPage.yCoordinateSpeech;
		}
	}

	private boolean nameHasMoreThanOneLine( )
	{
		return textSpecificationOutputName.getTextPerLine( ).size( ) > 1;
	}

	private int getXCoordinateSpeechCentered(String line){
		return getXCoordinateMiddlePointForTextSpeechBubble() - (getTextWidth( line )/2);
	}

	private int getXCoordinateMiddlePointForTextSpeechBubble(){
		return getXCoordinateMiddlePointForPosition();
	}


	private void placePosition(){
		setPositionTextConfiguration();
		setFontConfigurationPosition();
		drawPositionOnPhoto();
	}

	private void setPositionTextConfiguration(){
		textSpecificationOutputPosition = new AdaptTextTellMe().adaptPosition( tellMeModel.getPosition( ) );
	}

	private void drawPositionOnPhoto(){
		int yCoordinate = ImageRulesTellMeFrontPage.yCoordinateLine ;

		for (int i = textSpecificationOutputPosition.getTextPerLine().size()-1; i >= 0; i--){
			yCoordinate = yCoordinate - getSpaceForLineBreak();
			g.drawString( textSpecificationOutputPosition.getTextPerLine().get( i ),
				getXCoordinatePositionCentered(textSpecificationOutputPosition.getTextPerLine().get( i )), yCoordinate );
		}
	}

	private int getXCoordinatePositionCentered(String line){
		return getXCoordinateMiddlePointForPosition() - (getTextWidth( line )/2);
	}

	private int getXCoordinateMiddlePointForPosition(){
		return ImageRulesTellMeFrontPage.xCoordinateLine + ( ImageRulesTellMeFrontPage.lineLength /2);
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}

	private int getSpaceForLineBreak(){
		return textSpecificationOutputPosition.getTextSize( ) + 10;
	}

	private void setFontConfigurationPosition(){
		setFontPosition();
		setFontColorText();
	}

	private void setFontPosition(){
		g.setFont( new Font( ImageRulesTellMeFrontPage.font, Font.BOLD, textSpecificationOutputPosition.getTextSize() ) );
	}

	private boolean textSizeNameIsSmalerThanTextSizeSpeech( )
	{
		return textSpecificationOutputName.getTextSize( ) < textSpecificationOutputSpeech.getTextSize( );
	}

	private void setFontColorText(){
		g.setColor( Color.BLACK );
	}
}

