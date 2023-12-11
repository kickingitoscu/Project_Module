package de.fhws.fiw.instagram.campusTour;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPictureCampusTour
{
	private BufferedImage image;
	private Graphics2D g;
	private ImportDataOnePage importData;
	private TextSpecificationOutput textSpecificationOutput;
	private Image photo;


	public BufferedImage generateImage( ImportDataOnePage importData) throws IOException
	{
		setImportData( importData );
		configureGraphics( );
		placePhotoAsBackground();
		placeTitle();

		return image;
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( ImportDataOnePage importData ) {
		this.importData = importData;
	}

	private void placePhotoAsBackground()
	{
		adaptPhoto();
		drawPhotoOnImage();
	}

	private void adaptPhoto(){
		photo = new AdaptPhoto().getScaledImage( importData.getPhoto() );
	}

	private void drawPhotoOnImage(){
		g.drawImage( photo, 0 , 0, null);
	}

	private void placeTitle() throws IOException
	{
		setTitleTextConfiguration();
		setFontConfigurationTitle();
		setFrame();
		drawIconOnImage();
		setTitle();
	}

	private void setTitleTextConfiguration(){
		textSpecificationOutput = new AdaptTextCampusTour( ).adaptTitleConfig( importData.getTitle() ) ;
	}

	private void setFrame(){
		setFrameColor();
		drawFrameOnImage();
	}

	private void setFrameColor(){
		g.setColor( ImageRulesCampusTour.frameColor );
	}

	private void drawFrameOnImage(){
		g.fillRect( 0, getYCoordinateFrame() , getFrameWidth(), getFrameHeight());
	}

	private int getFrameWidth(){
		return ImageRulesCampusTour.xCoordinateStartTitle +
			getTextWidth( textSpecificationOutput.getTextPerLine().get( 0 ) ) + 120;
	}

	private int getYCoordinateFrame(){
		return ImageRulesCampusTour.yCoordinateStartTitle - textSpecificationOutput.getTextSize()  - getSpaceForFrameToText();
	}

	private int getFrameHeight(){
		int frameHeight = textSpecificationOutput.getTextSize();
		frameHeight = frameHeight + (textSpecificationOutput.getTextPerLine().size() * textSpecificationOutput.getTextSize() );
		return frameHeight;
	}

	private int getSpaceForFrameToText(){
		return 20;
	}

	private Image getIcon() throws IOException
	{
		return ImageIO.read( new File( ImageRulesCampusTour.iconPath ));
	}

	private void drawIconOnImage() throws IOException
	{
		g.drawImage( getIcon(), 20,getYCoordinateIcon(),ImageRulesCampusTour.iconWidth,ImageRulesCampusTour.iconHeight,
			null );
	}

	private int getYCoordinateIcon(){
		return getYCoordinateMiddlePointOfFrame() - (ImageRulesCampusTour.iconHeight/2);
	}

	private int getYCoordinateMiddlePointOfFrame(){
		return getYCoordinateFrame() + (getFrameHeight()/2);
	}

	private void setTitle(){
		setFontConfigurationTitle();
		drawTitleOnImage();
	}

	private void drawTitleOnImage(){
		int yCoordinate = ImageRulesCampusTour.yCoordinateStartTitle;

		for (int i = 0; i<textSpecificationOutput.getTextPerLine().size(); i++){
			g.drawString( textSpecificationOutput.getTextPerLine().get( i ), ImageRulesCampusTour.xCoordinateStartTitle,
				yCoordinate );
			yCoordinate = yCoordinate + getSpaceForLineBreak();
		}
	}

	private int getSpaceForLineBreak(){
		return textSpecificationOutput.getTextSize( ) + 5;
	}

	private void setFontConfigurationTitle(){
		setFontTitle();
		setFontColorText();
	}

	private void setFontTitle(){
		g.setFont( new Font( ImageRulesCampusTour.font, Font.BOLD, textSpecificationOutput.getTextSize()) );
	}

	private void setFontColorText(){
		g.setColor( ImageRulesCampusTour.textColor);
	}

	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}
}

