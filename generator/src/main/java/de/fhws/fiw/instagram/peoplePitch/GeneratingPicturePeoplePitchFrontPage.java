package de.fhws.fiw.instagram.peoplePitch;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneratingPicturePeoplePitchFrontPage
{
	private BufferedImage image;
	private Graphics2D g;
	private PeoplePitchModel peoplePitchModel;
	private TextSpecificationOutput textSpecificationOutputNameAndArea, textSpecificationOutputPeoplePitch;

	GeneratingPicturePeoplePitchFrontPage( ) //verhindert, dass ein "leeres" Objekt der Klasse erstellt wird, ohne den Builder aufzurufen
	{}

	public BufferedImage generatePhoto ( PeoplePitchModel peoplePitchModel )
	{
		setImportData( peoplePitchModel );
		configureGraphics( );
		placeBackgroundColor( );
		placeFrame();
		placePhoto();
		placeHeadline();
		placeNameAndArea();

		return image;
	}


	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( PeoplePitchModel peoplePitchModel ) {
		this.peoplePitchModel = peoplePitchModel;
	}

	private void placeBackgroundColor()
	{
		g.setColor( FHWS_Color.lightOrange );
		g.fillRect( 0,0,ImageRules.pictureWidth, ImageRules.pictureHeight );
	}

	private void placeFrame(){
		drawFrameOnImage();
		drawFillFrameOnImage();
	}

	private void drawFrameOnImage(){
		g.setColor( FHWS_Color.orange );
		g.fillRect( getFrameXCoordinate(), getFrameYCoordinate(), getFrameWidth(), getFrameHeight() );
	}

	private void drawFillFrameOnImage(){
		g.setColor( FHWS_Color.lightOrange );
		g.fillRect( getFillFrameXCoordinate(), getFillFrameYCoordinate(), getFillFrameWidth(),getFillFrameHeight() );
	}

	private int getFrameXCoordinate(){
		return ImageRulesPeoplePitchFrontPage.xCoordinateStartFrame;
	}

	private int getFrameYCoordinate(){
		return ImageRulesPeoplePitchFrontPage.yCoordinateStartFrame;
	}

	private int getFrameWidth(){
		return ImageRules.pictureWidth-2* ImageRulesPeoplePitchFrontPage.xCoordinateStartFrame;
	}

	private int getFrameHeight(){
		return ImageRulesPeoplePitchFrontPage.frameHeight;
	}

	private int getFillFrameXCoordinate(){
		return ImageRulesPeoplePitchFrontPage.xCoordinateStartFrame + ImageRulesPeoplePitchFrontPage.frameWidth;
	}

	private int getFillFrameYCoordinate(){
		return ImageRulesPeoplePitchFrontPage.yCoordinateStartFrame + ImageRulesPeoplePitchFrontPage.frameWidth;
	}

	private int getFillFrameWidth(){
		//660
		return ImageRules.pictureWidth-2* ImageRulesPeoplePitchFrontPage.xCoordinateStartFrame -2*
			ImageRulesPeoplePitchFrontPage.frameWidth;
	}

	private int getFillFrameHeight(){
		//660
		return ImageRulesPeoplePitchFrontPage.frameHeight -2* ImageRulesPeoplePitchFrontPage.frameWidth;
	}

	private void placePhoto()
	{
		scalePhoto();
		drawPhotoOnImage();
	}

	private int getXCoordinatePhoto(){
		return getFillFrameXCoordinate();
	}

	private int getYCoordinatePhoto(){
		return getFillFrameYCoordinate();
	}

	private void scalePhoto(){
		double scale = (double ) getFillFrameWidth() / (double) peoplePitchModel.getPhoto().getWidth( null ) ;

		Image scaledImage = peoplePitchModel.getPhoto().getScaledInstance(( int ) ( peoplePitchModel.getPhoto().getWidth( null )* scale), (int) (
			peoplePitchModel.getPhoto().getHeight( null )*scale), Image.SCALE_SMOOTH);
		peoplePitchModel.setPhoto( scaledImage );
	}

	private void drawPhotoOnImage(){
		g.drawImage( peoplePitchModel.getPhoto(), getXCoordinatePhoto() , getYCoordinatePhoto(), null);
	}

	private void placeHeadline( ){
		setPeoplePitchTextConfiguration();
		setFontConfigurationHeadline();
		drawHeadlineOnImage();
	}

	private void setPeoplePitchTextConfiguration(){
		textSpecificationOutputPeoplePitch = new AdaptText().adaptPeoplePitch( getTextPeoplePitch());
	}

	private void drawHeadlineOnImage(){
		g.drawString( textSpecificationOutputPeoplePitch.getTextPerLine().get( 0 ),
			ImageRulesPeoplePitchFrontPage.xCoordinateText,
			ImageRulesPeoplePitchFrontPage.yCoordinateHeadline );
	}

	private String getTextPeoplePitch(){
		return "# PEOPLE PITCH";
	}

	private void placeNameAndArea(){
		setFontConfigurationNameAndArea();
		setNameAndAreaTextConfiguration();
		drawFrameForNameAndArea();
		setFontConfigurationNameAndArea();
		drawNameAndAreaOnImage();
	}

	private void drawFrameForNameAndArea(){
		g.setColor( FHWS_Color.orange );
		g.fillRect( ImageRulesPeoplePitchFrontPage.xCoordinateText,
			ImageRulesPeoplePitchFrontPage.yCoordinateNameAndArea - ImageRulesPeoplePitchFrontPage.textSizeNameAndArea,
			calculateXSpaceForNameAndArea(), calculateYSpaceForNameAndArea());
	}

	private int calculateYSpaceForNameAndArea( ) {
		int frameHeight = 20; //Für den Rand

		for (int i = 0; i < textSpecificationOutputNameAndArea.getTextPerLine().size(); i++){
			frameHeight = frameHeight + textSpecificationOutputNameAndArea.getTextSize();
		}

		return frameHeight;
	}

	private int calculateXSpaceForNameAndArea(){
		int frameWidth = g.getFontMetrics().stringWidth( textSpecificationOutputNameAndArea.getTextPerLine().get( 0) ); //Für den Rand
		int frameWidthActualLine;

		for (int i = 1; i < textSpecificationOutputNameAndArea.getTextPerLine().size(); i++){
			frameWidthActualLine = g.getFontMetrics().stringWidth( textSpecificationOutputNameAndArea.getTextPerLine().get( i ) );
			if (frameWidthActualLine> frameWidth){
				frameWidth = frameWidthActualLine;
			}
		}

		return frameWidth + 40;
	}

	private void drawNameAndAreaOnImage(){
		int yCoordinate = ImageRulesPeoplePitchFrontPage.yCoordinateNameAndArea;

		for (int i = 0; i < textSpecificationOutputNameAndArea.getTextPerLine().size(); i++){
			g.drawString( textSpecificationOutputNameAndArea.getTextPerLine().get( i ),
				ImageRulesPeoplePitchDetailPage.xCoordinateText + getSpaceToMoveInNameAndArea(), yCoordinate );
			yCoordinate = yCoordinate + ImageRulesPeoplePitchFrontPage.textSizeNameAndArea;
		}
	}

	private void setNameAndAreaTextConfiguration(){
		textSpecificationOutputNameAndArea = new AdaptText().adaptNameAndArea( peoplePitchModel.getName( ) + " / "+ peoplePitchModel.getArea() );
	}

	private void setFontConfigurationNameAndArea(){
		g.setColor( Color.white );
		g.setFont( new Font( ImageRulesPeoplePitchFrontPage.font, Font.BOLD, ImageRulesPeoplePitchFrontPage.textSizeNameAndArea ));
	}

	private void setFontConfigurationHeadline(){
		g.setColor( FHWS_Color.orange );
		g.setFont( new Font( ImageRulesPeoplePitchFrontPage.font, Font.BOLD, textSpecificationOutputPeoplePitch.getTextSize()) );
	}

	private int getSpaceToMoveInNameAndArea(){
		return 20;
	}
}

