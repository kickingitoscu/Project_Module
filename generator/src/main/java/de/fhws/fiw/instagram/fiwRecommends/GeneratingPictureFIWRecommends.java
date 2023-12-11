package de.fhws.fiw.instagram.fiwRecommends;

import de.fhws.fiw.instagram.information.AdaptPhoto;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GeneratingPictureFIWRecommends
{
	private BufferedImage image;
	private Graphics2D g;
	private FiwRecommendsModel importData;
	private TextSpecificationOutput textSpecificationOutput;
	private Image inputImage;


	public BufferedImage generateImage( FiwRecommendsModel importData) throws IOException
	{
		configureGraphics( );
		setImportData( importData );
		drawTemplateOnImage();
		placePhotoOnImage();
		placeTextOnImage();

		return image;
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( FiwRecommendsModel importData ) {
		this.importData = importData;
	}

	private void drawTemplateOnImage() throws IOException
	{
		g.drawImage( generateBackgroundImage(),0,0,ImageRules.pictureWidth,ImageRules.pictureHeight, null );
	}

	private Image generateBackgroundImage() throws IOException
	{
		return new GeneratingBackgroundImageFIWRecommends().generateImage();
	}

	private void placePhotoOnImage(){
		adaptPhoto();
		drawPhotoOnImage();
	}

	private void adaptPhoto(){
		inputImage = new AdaptPhoto().getScaledImage( importData.getImage() );
	}

	private void drawPhotoOnImage(){
		g.drawImage( inputImage,ImageRulesFIWRecommends.borderFrameWidth,ImageRulesFIWRecommends.borderFrameHeight,null);
	}

	private void placeTextOnImage(){
		placeHeadlineOnImage();
		placeInputTextOnImage();
	}

	private void placeHeadlineOnImage(){
		setFrameForHeadline();
		setHeadline();
	}

	private void setFrameForHeadline(){
		setFrameColor();
		drawFrameForHeadlineOnImage();
	}

	private void drawFrameForHeadlineOnImage(){
		g.fillRect( getXCoordinateTextFrame(), getYCoordinateHeadlineFrame(), ImageRulesFIWRecommends.textFrameWidth,
			ImageRulesFIWRecommends.textFrameHeightHeadline  );
	}

	private int getXCoordinateTextFrame(){
		return ImageRulesFIWRecommends.xCoordinateStartHeadlineAndTextFrame;
	}

	private int getYCoordinateHeadlineFrame(){
		return ImageRulesFIWRecommends.yCoordinateStartHeadlineFrame;
	}

	private void setHeadline(){
		setFontConfigurationHeadline();
		drawHeadlineOnImage();
	}

	private void setFontConfigurationHeadline(){
		setFontColorText();
		setFontHeadline();
	}

	private void setFontHeadline(){
		g.setFont( new Font( ImageRulesFIWRecommends.font, Font.BOLD, ImageRulesFIWRecommends.textSizeFIWRecommends ) );
	}

	private void drawHeadlineOnImage(){
		g.drawString( ImageRulesFIWRecommends.yourTips, getXCoordinateForCenteredHeadline(),getYCoordinateHeadline() );
	}

	private int getXCoordinateForCenteredHeadline(){
		return getXCoordinateMiddlePointOfImage() - getTextWidth( ImageRulesFIWRecommends.yourTips )/2;
	}

	private int getYCoordinateHeadline(){
		return ImageRulesFIWRecommends.yCoordinateStartHeadlineFrame + ImageRulesFIWRecommends.textSizeFIWRecommends
			+ ImageRulesFIWRecommends.textSizeFIWRecommends/3;
	}

	private void placeInputTextOnImage(){
		setTextSpecificationOutput();
		setFontConfigurationText();
		setFrame();
		setTextOnImage();
	}

	private void setTextSpecificationOutput(){
		textSpecificationOutput = new AdaptText().adaptTextConfig( importData.getHeadline() );
	}

	private void setFontConfigurationText(){
		setFontText();
		setFontColorText();
	}

	private void setFontText(){
		g.setFont( new Font( ImageRulesFIWRecommends.font, Font.BOLD,textSpecificationOutput.getTextSize() ) );
	}

	private void setFrame(){
		setFrameColor();
		drawFrameOnImage();
	}

	private void setFrameColor(){
		g.setColor( ImageRulesFIWRecommends.frameColor );
	}

	private void drawFrameOnImage(){
		g.fillRect( getXCoordinateTextFrame(), getYCoordinateTextFrame(), ImageRulesFIWRecommends.textFrameWidth,
			getTextFrameHeight()  );
	}

	private int getYCoordinateTextFrame(){
		return ImageRulesFIWRecommends.yCoordinateStartTextFrame;
	}


	private int getTextFrameHeight(){
		int frameHeight = textSpecificationOutput.getTextSize();
		frameHeight = frameHeight + (textSpecificationOutput.getTextPerLine().size() * textSpecificationOutput.getTextSize() );
		return frameHeight;
	}

	private void setTextOnImage(){
		setFontColorText();
		drawTextOnImage();
	}

	private void setFontColorText(){
		g.setColor( ImageRulesFIWRecommends.textColor );
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
		return ImageRulesFIWRecommends.yCoordinateStartTextFrame + textSpecificationOutput.getTextSize() + 20;
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

