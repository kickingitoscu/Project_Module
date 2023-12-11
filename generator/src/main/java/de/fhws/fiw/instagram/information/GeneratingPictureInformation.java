package de.fhws.fiw.instagram.information;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPictureInformation
{
	private BufferedImage image;
	private Graphics2D g;
	private InformationModel informationModel;
	private TextSpecificationOutput textSpecificationOutput;
	private Image photo;


	public BufferedImage generateImage( InformationModel informationModel ) throws IOException
	{
		setImportData( informationModel );
		configureGraphics( );
		drawImageAsBackgroundOnImage();
		placePhotoOnImage();
		placeIconOnImage();
		placeTextOnImage();

		return image;
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( InformationModel informationModel ) {
		this.informationModel = informationModel;
	}

	private void drawImageAsBackgroundOnImage() throws IOException
	{
		g.drawImage( getBackgroundImage(), 0 , 0, null);
	}

	private Image getBackgroundImage() throws IOException
	{
		return ImageIO.read( new File( ImageRulesInformation.backgroundPath ));
	}

	private void placePhotoOnImage(){
		adaptPhoto();
		drawPhotoOnImage();
	}

	private void adaptPhoto(){
		photo = new AdaptPhoto().getScaledImage( informationModel.getImage() );
	}

	private void drawPhotoOnImage(){
		g.drawImage(photo,ImageRulesInformation.frame,ImageRulesInformation.frame,null);
	}

	private void placeIconOnImage() throws IOException
	{
		g.drawImage( getIcon(), getXCoordinateIcon(), getYCoordinateIcon(), ImageRulesInformation.iconWidth,
			ImageRulesInformation.iconHeight, null );
	}

	private int getXCoordinateIcon(){
		return ImageRulesInformation.frame - (ImageRulesInformation.iconWidth/2);
	}

	private int getYCoordinateIcon(){
		return ImageRulesInformation.frame + getPhotoSideLength() - (ImageRulesInformation.iconHeight/2);
	}

	private int getPhotoSideLength(){
		return ImageRules.pictureWidth-(2*ImageRulesInformation.frame);
	}

	private Image getIcon() throws IOException
	{
		return ImageIO.read( new File( ImageRulesInformation.iconPath ) );
	}

	private void placeTextOnImage(){
		setTextSpecificationOutput();
		setFontConfigurationText();
		setFrame();
		setTextOnImage();
	}

	private void setTextSpecificationOutput(){
		textSpecificationOutput = new AdaptText().adaptTextConfig( informationModel.getText() );
	}

	private void setFontConfigurationText(){
		setFontText();
		setFontColorText();
	}

	private void setFontText(){
		g.setFont( new Font( ImageRulesInformation.font, Font.BOLD,textSpecificationOutput.getTextSize() ) );
	}

	private void setFrame(){
		setFrameColor();
		drawFrameOnImage();
	}

	private void setFrameColor(){
		g.setColor( ImageRulesInformation.frameColor );
	}

	private void drawFrameOnImage(){
		g.fillRoundRect( getXCoordinateTextFrame(), getYCoordinateTextFrame(), ImageRulesInformation.frameWidth,
			getFrameHeight(), 30,30  );
	}

	private int getXCoordinateTextFrame(){
		return ImageRulesInformation.xCoordinateStartTextFrame;
	}

	private int getYCoordinateTextFrame(){
		return ImageRulesInformation.yCoordinateStartTextFrame;
	}

	private int getFrameHeight(){
		int frameHeight = textSpecificationOutput.getTextSize();
		frameHeight = frameHeight + (textSpecificationOutput.getTextPerLine().size() * textSpecificationOutput.getTextSize() );
		return frameHeight;
	}

	private void setTextOnImage(){
		setFontColorText();
		drawTextOnImage();
	}

	private void setFontColorText(){
		g.setColor( ImageRulesInformation.textColor );
	}

	private void drawTextOnImage(){
		int yCoordinate = getYCoordinateStartText();

		for (int i = 0; i<textSpecificationOutput.getTextPerLine().size(); i++){
			g.drawString( textSpecificationOutput.getTextPerLine().get( i ), getXCoordinateText( i ),
				yCoordinate );
			yCoordinate = yCoordinate + textSpecificationOutput.getTextSize();
		}
	}

	private int getYCoordinateStartText(){
		return ImageRulesInformation.yCoordinateStartTextFrame + textSpecificationOutput.getTextSize() + 20;
	}

	private int getXCoordinateText(int i){
		return getXCoordinateMiddlePointOfImage() - (getTextWidth( textSpecificationOutput.getTextPerLine().get( i ) )/2);
	}

	private int getXCoordinateMiddlePointOfImage(){
		return ImageRules.pictureWidth/2;
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}
}

