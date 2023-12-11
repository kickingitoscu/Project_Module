package de.fhws.fiw.instagram.reminder;

import de.fhws.fiw.instagram.campusTour.AdaptPhoto;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Links für die Erstellung von der Rahmen
// https://mathbits.com/MathBits/Java/Graphics/GraphingFill.htm
// http://cs111.wellesley.edu/~cs111/archive/cs111_fall06/public_html/labs/lab12/arc.html

public class GeneratingPictureReminder
{
	private BufferedImage image;
	private Graphics2D g;
	private ReminderModel reminderModel;
	private TextSpecificationOutput textSpecificationOutputReminder, textSpecificationOutputOkay, textSpecificationOutputText;
	private Image photo;


	public BufferedImage generateImage( ReminderModel reminderModel ) throws IOException
	{
		setImportData( reminderModel );
		configureGraphics( );
		placePhotoAsBackground();
		placeFramesOnImage();
		placeHeadersOnImage();
		placeTextOnImage();

		return image;
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( ReminderModel reminderModel ) {
		this.reminderModel = reminderModel;
	}

	private void placePhotoAsBackground() {
		adaptPhoto();
		drawPhotoOnImage();
	}

	private void adaptPhoto(){
		photo = new AdaptPhoto().getScaledImage( reminderModel.getPhoto() );
	}

	private void drawPhotoOnImage(){
		g.drawImage( photo, 0 , 0, null);
	}

	private void placeFramesOnImage() throws IOException
	{
		setFrame();
		drawIconOnImage();
	}

	private void setFrame(){
		setFrameColor();
		drawFramesOnImage();
	}

	private void setFrameColor(){
		g.setColor( ImageRulesReminder.frameColor );
	}

	private void drawFramesOnImage(){
		drawReminderFrameOnImage();
		drawIconFrameOnImage();
		drawOkayFrameOnImage();
	}

	private void drawReminderFrameOnImage(){
		g.fillRoundRect( ImageRulesReminder.xCoordinateStartTextFrame, ImageRulesReminder.yCoordinateStartTextFrame,
			ImageRulesReminder.frameWidthText, ImageRulesReminder.frameHeightText,getArcWidth(),getArcHeight());
	}

	private void drawIconFrameOnImage(){
		g.fillArc( getXCoordinateIconFrame(), getYCoordinateIconFrame(),ImageRulesReminder.frameWidthIcon,ImageRulesReminder.frameHeightIcon,0,180 );
	}

	private void drawOkayFrameOnImage(){
		g.fillRoundRect( ImageRulesReminder.xCoordinateStartOkayFrame, ImageRulesReminder.yCoordinateStartOkayFrame,
			ImageRulesReminder.frameWidthOkay, ImageRulesReminder.frameHeightOkay, getArcWidth(), getArcHeight());
	}

	private int getArcWidth(){
		return ImageRulesReminder.arcWidthFrame;
	}

	private int getArcHeight(){
		return ImageRulesReminder.arcHeightFrame;
	}

	private int getYCoordinateIconFrame(){
		return ImageRulesReminder.yCoordinateStartTextFrame-(ImageRulesReminder.frameHeightIcon /2);
	}

	private int getXCoordinateIconFrame(){
		return getXCoordinateMiddlePoint() - (ImageRulesReminder.frameWidthIcon /2);
	}

	private int getXCoordinateMiddlePoint (){
		return ImageRules.pictureWidth/2;
	}

	private Image getIcon() throws IOException
	{
		return ImageIO.read( new File( ImageRulesReminder.iconPath ));
	}

	private void drawIconOnImage() throws IOException
	{
		g.drawImage( getIcon(), getXCoordinateIcon(),getYCoordinateIcon(),ImageRulesReminder.iconWidth,ImageRulesReminder.iconHeight, null );
	}

	private int getXCoordinateIcon(){
		return getXCoordinateMiddlePoint()-ImageRulesReminder.iconWidth/2;
	}

	private int getYCoordinateIcon(){
		return ImageRulesReminder.yCoordinateStartTextFrame - ImageRulesReminder.frameHeightIcon/2+5;
	}

	private void placeHeadersOnImage()
	{
		placeReminderTextOnImage();
		placeOkayTextOnImage();
	}

	private void placeReminderTextOnImage(){
		setReminderTextConfiguration();
		setFontConfigurationReminder();
		drawReminderOnImage();
	}

	private void setReminderTextConfiguration(){
		textSpecificationOutputReminder = new AdaptTextReminder().adaptReminderConfig( getTextReminder() );
	}

	private void setFontConfigurationReminder(){
		setFontReminder();
		setFontColorHeaders();
	}

	private void drawReminderOnImage(){
		g.drawString( getTextReminder(), getXCoordinateReminder(), getYCoordinateReminder());
	}

	private int getXCoordinateReminder(){
		return getXCoordinateMiddlePoint() - (getTextWidth( getTextReminder())/2);
	}

	private int getYCoordinateReminder(){
		return ImageRulesReminder.yCoordinateStartTextFrame + textSpecificationOutputReminder.getTextSize()+10;
	}

	private String getTextReminder(){
		return ImageRulesReminder.reminder;
	}

	private void setFontReminder(){
		g.setFont( new Font( ImageRulesReminder.font, Font.BOLD, textSpecificationOutputReminder.getTextSize()) );
	}

	private void placeOkayTextOnImage(){
		setOkayTextConfiguration();
		setFontConfigurationOkay();
		drawOkayOnImage();
	}

	private void setOkayTextConfiguration(){
		textSpecificationOutputOkay = new AdaptTextReminder().adaptOkayConfig( ImageRulesReminder.okay );
	}

	private void setFontConfigurationOkay(){
		setFontOkay();
		setFontColorHeaders();
	}

	private void setFontOkay(){
		g.setFont( new Font( ImageRulesReminder.font, Font.BOLD, textSpecificationOutputOkay.getTextSize()) );
	}

	private void drawOkayOnImage(){
		g.drawString( getTextOkay(), getXCoordinateOkay(), getYCoordinateOkay());
	}

	private int getXCoordinateOkay(){
		return getXCoordinateMiddlePoint() - (getTextWidth( getTextOkay())/2);
	}

	private String getTextOkay(){
		return textSpecificationOutputOkay.getTextPerLine().get( 0 );
	}

	private int getYCoordinateOkay(){
		return ImageRulesReminder.yCoordinateStartOkayFrame + textSpecificationOutputOkay.getTextSize() + 10;
	}

	private void setFontColorHeaders(){
		g.setColor( ImageRulesReminder.textColorReminder);
	}

	private void placeTextOnImage(){
		setTextConfiguration();
		setFontConfigurationText();
		drawTextOnImage();
	}

	private void setTextConfiguration(){
		textSpecificationOutputText = new AdaptTextReminder().adaptTextConfig( reminderModel.getText( ) );
	}

	private void setFontConfigurationText(){
		setFontColorText();
		setFontText();
	}

	private void setFontColorText(){
		g.setColor( ImageRulesReminder.textColor );
	}

	private void setFontText(){
		g.setFont( new Font( ImageRulesReminder.font,Font.BOLD,textSpecificationOutputText.getTextSize() ) );
	}

	private void drawTextOnImage(){
		int yCoordinate = getYCoordinateText();

		for (int i = 0; i<textSpecificationOutputText.getTextPerLine().size(); i++){
			g.drawString( textSpecificationOutputText.getTextPerLine().get( i ), getXCoordinateText( i ),yCoordinate);
			yCoordinate = yCoordinate + textSpecificationOutputText.getTextSize();
		}
	}

	private int getYCoordinateText(){
		if( textHasMoreThanOneLine( ) ){
			return ImageRulesReminder.yCoordinateMiddlePointOfFrame + 20;
		}
		else{
			return ImageRulesReminder.yCoordinateMiddlePointOfFrame + textSpecificationOutputText.getTextSize();
		}
	}

	private boolean textHasMoreThanOneLine( )
	{
		return textSpecificationOutputText.getTextPerLine( ).size( ) > 1;
	}

	private int getXCoordinateText(int i){
		return getXCoordinateMiddlePoint()-(getTextWidth( textSpecificationOutputText.getTextPerLine().get( i ) )/2);
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}
}

