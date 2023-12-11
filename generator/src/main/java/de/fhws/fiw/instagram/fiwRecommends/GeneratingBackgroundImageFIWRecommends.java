package de.fhws.fiw.instagram.fiwRecommends;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GeneratingBackgroundImageFIWRecommends
{

	private BufferedImage image;
	private Graphics2D g;


	public BufferedImage generateImage( ) throws IOException
	{
		configureGraphics( );
		generateBackground();

		return image;
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void generateBackground(){
		drawColoredBackground();
		drawFIWRecommendsTextOnImage();
		placeColoredCorners();
	}

	private void drawColoredBackground()
	{
		g.setColor( FHWS_Color.lightOrange );
		g.fillRect( 0, 0 , ImageRules.pictureWidth, ImageRules.pictureHeight );
	}

	private void drawFIWRecommendsTextOnImage(){
		g.setColor( FHWS_Color.orange );
		g.setFont( new Font( ImageRulesFIWRecommends.font, Font.BOLD, ImageRulesFIWRecommends.textSizeFIWRecommends ) );
		g.drawString( ImageRulesFIWRecommends.fiwRecommends, ImageRulesFIWRecommends.borderFrameWidth, getYCoordinateFIWRecommendsText());
	}

	private void placeColoredCorners(){
		setColorForColoredCorners();
		placeUpperColoredCornerOnImage();
		placeLowerColoredCornerOnImage();
	}

	private void setColorForColoredCorners(){
		g.setColor( FHWS_Color.orange );
	}

	private void placeUpperColoredCornerOnImage(){
		drawHorizontalLineUpperColoredCorner();
		drawVerticalLineUpperColoredCorner();
	}

	private void drawHorizontalLineUpperColoredCorner(){
		g.fillRoundRect( ImageRulesFIWRecommends.frameForCorners, ImageRulesFIWRecommends.frameForCorners,
			ImageRulesFIWRecommends.cornerLinesLength, ImageRulesFIWRecommends.cornerLinesWidth,
			ImageRulesFIWRecommends.arcValueCornerLines,ImageRulesFIWRecommends.arcValueCornerLines );
	}

	private void drawVerticalLineUpperColoredCorner(){
		g.fillRoundRect( ImageRulesFIWRecommends.frameForCorners, ImageRulesFIWRecommends.frameForCorners,
			ImageRulesFIWRecommends.cornerLinesWidth, ImageRulesFIWRecommends.cornerLinesLength,
			ImageRulesFIWRecommends.arcValueCornerLines,ImageRulesFIWRecommends.arcValueCornerLines );
	}

	private void placeLowerColoredCornerOnImage(){
		drawHorizontalLineLowerColoredCorner();
		drawVerticalLineLowerColoredCorner();
	}

	private void drawHorizontalLineLowerColoredCorner(){
		g.fillRoundRect( getXCoordinateHorizontalLineLowerColoredCorner(), getYCoordinateHorizontalLineLowerColoredCorner(),
			getLineLength(), ImageRulesFIWRecommends.cornerLinesWidth,
			ImageRulesFIWRecommends.arcValueCornerLines,ImageRulesFIWRecommends.arcValueCornerLines );
	}

	private int getXCoordinateHorizontalLineLowerColoredCorner(){
		return ImageRules.pictureWidth-ImageRulesFIWRecommends.frameForCorners - getLineLength();
	}

	private int getYCoordinateHorizontalLineLowerColoredCorner(){
		return ImageRules.pictureHeight - ImageRulesFIWRecommends.frameForCorners - ImageRulesFIWRecommends.cornerLinesWidth;
	}

	private int getLineLength(){
		return ImageRules.pictureWidth - ImageRulesFIWRecommends.borderFrameWidth - ImageRulesFIWRecommends.frameForCorners -
			getTextWidth( ImageRulesFIWRecommends.fiwRecommends ) - getFreeSpaceToStartLine() ;
	}

	private int getFreeSpaceToStartLine(){
		return 20;
	}

	private void drawVerticalLineLowerColoredCorner(){
		g.fillRoundRect( getXCoordinateVerticalLineLowerColoredCorner(), getYCoordinateVerticalLineLowerColoredCorner(),
			ImageRulesFIWRecommends.cornerLinesWidth, ImageRulesFIWRecommends.cornerLinesLength,
			ImageRulesFIWRecommends.arcValueCornerLines,ImageRulesFIWRecommends.arcValueCornerLines );
	}

	private int getXCoordinateVerticalLineLowerColoredCorner(){
		return ImageRules.pictureWidth - ImageRulesFIWRecommends.frameForCorners - ImageRulesFIWRecommends.cornerLinesWidth;
	}

	private int getYCoordinateVerticalLineLowerColoredCorner(){
		return ImageRules.pictureHeight - ImageRulesFIWRecommends.frameForCorners - ImageRulesFIWRecommends.cornerLinesLength;
	}

	private int getYCoordinateFIWRecommendsText(){
		return ImageRulesFIWRecommends.borderFrameHeight + ImageRulesFIWRecommends.imageHeight + ImageRulesFIWRecommends.textSizeFIWRecommends;
	}
	private int getTextWidth(String line){
		return g.getFontMetrics().stringWidth( line );
	}
}
