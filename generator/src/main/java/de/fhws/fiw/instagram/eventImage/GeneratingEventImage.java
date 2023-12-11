package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GeneratingEventImage
{
	private BufferedImage image;
	private Graphics2D g;
	private int yCoordinateText;
	private ComponentPropertiesConfig componentPropertiesConfig;
	private ColorConfig colorConfig;
	private EventImageModel eventImageModel;
	private TextSpecificationOutput  finalTextSpecificationHeader, finalTextSpecificationSubheader, finalTextSpecificationContent;


	public GeneratingEventImage(){
		initializeGraphics();
	}

	private void initializeGraphics(){
		image = new BufferedImage(ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g =(Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	public BufferedImage generatePicture ( EventImageModel eventImageModel, ColorConfig colorConfig, ComponentPropertiesConfig componentPropertiesConfig) throws IOException
	{
		this.componentPropertiesConfig = componentPropertiesConfig;
		this.colorConfig = colorConfig;
		this.eventImageModel = eventImageModel;

		setBasicFrameworkOfEventImage();
		setInputTextOfUser();

		return image;
	}

	private void setBasicFrameworkOfEventImage() throws IOException {
		setBackgroundColor();
		setFrames();
		placeLine();
		setLogo();
	}

	private void setInputTextOfUser(){
		setHost( );
		adaptHeaderText( );
		adaptSubheadertext();
		adaptContent();
		placeHeader( );
		placeSubheader( );
		placeContent();
		placeTimeAndLocation();
	}

	private void setBackgroundColor (){
		g.setColor( colorConfig.getBackgroundColor());
		g.fillRect(0, 0, ImageRules.pictureWidth, ImageRules.pictureHeight);
	}

	private void setFrames(){
		drawFrame();
		drawFillFrame();
	}

	private void drawFrame(){
		setColorForFrame();
		drawFrameOnImage();
	}

	private void drawFillFrame(){
		setColorForFillFrame();
		drawFillFrameOnImage();
	}

	private void drawFrameOnImage(){
		g.fillRect( componentPropertiesConfig.getFrameXCoordinate(), componentPropertiesConfig.getFrameYCoordinate(),
			ImageRulesEventImage.frameWidth, ImageRulesEventImage.frameHeight );
	}

	private void setColorForFrame(){
		g.setColor( colorConfig.getFrameColor());
	}

	private void setColorForFillFrame(){
		g.setColor( colorConfig.getFillFrameColor());
	}

	private void drawFillFrameOnImage(){
		g.fillRect( componentPropertiesConfig.getFillframeXCoordinate(), componentPropertiesConfig.getFillframeYCoordinate(),
			ImageRulesEventImage.fillFrameWidth, ImageRulesEventImage.fillFrameHeight );
	}

	private void placeLine( ) {
		g.setColor( colorConfig.getLineColor() );
		g.setStroke( new BasicStroke( 4f ) );
		g.drawLine( 100, 910,980, 910 );
	}

	private void setHost ( ){
		setColorHost();
		setFontHost();
		drawHostOnImage();
	}

	private void setLogo( ) throws IOException {
		String logopath = "";
		switch ( colorConfig.getLogoColor() ){
		case BLACK: logopath = "/Logo_schwarz.png";
			break;
		case ORANGE: logopath = "/Logo_orange.png";
			break;
		case WHITE: logopath = "/Logo_weiss.png";
		}

		Image logoImage =  ImageIO.read( this.getClass().getResourceAsStream( logopath ) );

		drawLogoOnPicture( logoImage );
	}

	private void drawLogoOnPicture(Image logoImage){
		g.drawImage( logoImage, ImageRulesEventImage.logoXCoordinate, ImageRulesEventImage.logoYCoordinate,
			ImageRules.logoWidth,ImageRules.logoHeight,null);
	}

	private void setColorHost(){
		g.setColor( colorConfig.getHostColor());
	}

	private void setFontHost(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getHostFontStyle(),componentPropertiesConfig.getHostTextSize() ) );
	}

	private void drawHostOnImage(){
		yCoordinateText = ImageRulesEventImage.freeSpaceYToTextStart;
		g.drawString( eventImageModel.getHost( ), ImageRulesEventImage.freeSpaceXToTextStart, yCoordinateText );
	}

	private void placeHeader(){
		setColorHeader();
		setFontHeader();
		drawHeaderOnPhoto();
	}

	private void placeSubheader(){
		setColorSubheader();
		setFontSubheader();
		drawSubheaderOnPhoto();
	}

	private void placeContent(){
		setColorContent();
		setFontContent();
		drawContentOnPhoto();
	}

	private void placeTimeAndLocation(){
		setColorTimeAndLocation();
		setFontTimeAndLocation();
		drawTimeAndLocationOnPhoto(combineTimeAndLocationForImage());
	}

	private String combineTimeAndLocationForImage( )
	{
		return eventImageModel.getTime() + " / " + eventImageModel.getLocation( );
	}

	private void drawTimeAndLocationOnPhoto(String timeAndLocation )
	{
		g.drawString( timeAndLocation, componentPropertiesConfig.getFrameXCoordinate(), componentPropertiesConfig.getTimeAndLocationYCoordinate() );
	}

	private void drawHeaderOnPhoto(){
		generateSpaceBetweenTextModules();

		for (int i = 0; i < finalTextSpecificationHeader.getTextPerLine().size(); i++){
			g.drawString( finalTextSpecificationHeader.getTextPerLine().get( i ),
				ImageRulesEventImage.freeSpaceXToTextStart, yCoordinateText );
			yCoordinateText = yCoordinateText + finalTextSpecificationHeader.getTextSize();
		}
	}

	private void drawSubheaderOnPhoto(){
		for (int i = 0; i < finalTextSpecificationSubheader.getTextPerLine().size(); i++)
		{
			g.drawString( finalTextSpecificationSubheader.getTextPerLine( ).get( i ),
				ImageRulesEventImage.freeSpaceXToTextStart, yCoordinateText );
			yCoordinateText = yCoordinateText + finalTextSpecificationSubheader.getTextSize( );
		}
	}

	private void drawContentOnPhoto(){
		yCoordinateText = yCoordinateText + getFreeSpaceBetweenTextModules();

		for(int i= 0; i<finalTextSpecificationContent.getTextPerLine().size(); i++){
			g.drawString( finalTextSpecificationContent.getTextPerLine().get( i ),
				ImageRulesEventImage.freeSpaceXToTextStart, yCoordinateText);
			yCoordinateText = yCoordinateText + finalTextSpecificationContent.getTextSize()+ getAdditionalSpaceBetweenLinesOfContent();
		}
	}

	private int getAdditionalSpaceBetweenLinesOfContent(){
		return 10;
	}

	private void generateSpaceBetweenTextModules(){
		yCoordinateText = yCoordinateText + 3* getFreeSpaceBetweenTextModules();
	}

	private int getFreeSpaceBetweenTextModules(){
		return 30;
	}

	private void setColorSubheader(){
		g.setColor( colorConfig.getSubheaderColor() );
	}

	private void adaptHeaderText(){
		finalTextSpecificationHeader = new AdaptTextEventImage(componentPropertiesConfig).adaptHeaderConfig( eventImageModel.getHeader( ) );
	}

	private void adaptSubheadertext(){
		finalTextSpecificationSubheader = new AdaptTextEventImage( componentPropertiesConfig ).adaptSubheaderConfig( eventImageModel.getSubheader( ) );
	}

	private void setFontSubheader(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getSubheaderFontStyle(),
			finalTextSpecificationSubheader.getTextSize() ));
	}

	private void adaptContent(){
		finalTextSpecificationContent = new AdaptContent().adaptContent( componentPropertiesConfig,
			eventImageModel.getContent( ) );
	}

	private void setFontHeader(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getHeaderFontStyle(),
			finalTextSpecificationHeader.getTextSize()));
	}

	private void setColorHeader(){
		g.setColor(colorConfig.getHeaderColor());
	}

	private void setFontContent(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getContentFontStyle(),
			finalTextSpecificationContent.getTextSize()) );
	}

	private void setColorContent(){
		g.setColor( colorConfig.getContentColor() );
	}

	private void setColorTimeAndLocation(){
		g.setColor( colorConfig.getTimeANDloctionColor() );
	}

	private void setFontTimeAndLocation(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getTimeAndLocationFontStyle(),
			componentPropertiesConfig.getTimeAndLocationTextSize() ) );
	}

}
