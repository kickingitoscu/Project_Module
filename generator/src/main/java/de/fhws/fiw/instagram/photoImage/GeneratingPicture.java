package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GeneratingPicture
{
	private BufferedImage image;
	private Graphics2D g;
	private Logo logo;

	private PhotoImageModel data;
	private ColorConfig colorConfig;
	private ComponentPropertiesConfig componentPropertiesConfig;
	private TextSpecificationOutput finalTextSpecificationHeader, finalTextSpecificationSubheader;
	private int yCoordinateHeaders, frameSize, yCoordinateFrameStart;

	private GeneratingPicture( ) //verhindert, dass ein "leeres" Objekt der Klasse erstellt wird, ohne den Builder aufzurufen
	{
	}

	public BufferedImage generatePhoto () throws IOException {
		configureGraphics( );
		placePicture( );
		placeLogo( );
		adaptHeaderText();
		adaptSubheaderText();
		calculateFrameSize();
		placeFrame( );
		placeHeader( );
		placeSubheader( );

		return image;
	}



	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
		//g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
	}

	private void setData( PhotoImageModel data ) {
		this.data = data;
	}

	private void setColorConfig( ColorConfig colorConfig )
	{
		this.colorConfig = colorConfig;
	}

	private void setComponentPropertiesConfig( ComponentPropertiesConfig componentPropertiesConfig ) {
		this.componentPropertiesConfig = componentPropertiesConfig;
	}

	private void placePicture()
	{
		ImageConfiguration imageConfiguration = new AdaptPhoto().getImageConfiguration(data.getImportedImage(),
			componentPropertiesConfig );
		g.drawImage( imageConfiguration.getImage(), imageConfiguration.getXCoordinate(),
			imageConfiguration.getYCoordinate( ), null);
	}


	private void calculateFrameSize()
	{
		frameSize = calculateSpaceForHeadlines() + getFreeYSpaceForFrameInFrame();
	}

	private int getFreeYSpaceForFrameInFrame( ) {
		return ImageRulesPhotoImage.freeSpaceToTextStart;
	}

	private int calculateSpaceForHeadlines(){
		return calculateSpaceForHeader() + getFreeSpaceBetweenHeadlines() + calculateSpaceForSubheader();
	}

	private int calculateSpaceForHeader( ) {
		int headerHeight= g.getFont().getSize();

		//Die Angabe der Y-Coordinate ist Unterkante Zeile
		for (int i = 0; i < finalTextSpecificationHeader.getTextPerLine().size(); i++){
			headerHeight = headerHeight + finalTextSpecificationHeader.getTextSize();
		}

		return headerHeight;
	}

	private int calculateSpaceForSubheader(){
		int subHeaderHeight = 0;

		for (int i = 0; i < finalTextSpecificationSubheader.getTextPerLine().size(); i++){
			subHeaderHeight = subHeaderHeight + finalTextSpecificationSubheader.getTextSize();
		}

		return subHeaderHeight;
	}

	private int getFreeSpaceBetweenHeadlines(){
		return 15;
	}

	private void placeFrame(){
		g.setColor( colorConfig.getFrameColor() );

		if( framePositionIsBottomOfPicture( ) ){
			yCoordinateFrameStart = ImageRules.pictureHeight - getFreeSpaceToPictureBorder() - frameSize;
		}
		else{
			yCoordinateFrameStart = getFreeSpaceToPictureBorder();
		}

		g.fillRect( ImageRulesPhotoImage.freeSpaceToFrameStart, yCoordinateFrameStart, getWidthForFrame(), frameSize);

	}

	private int getWidthForFrame(){
		return ImageRules.pictureWidth- ImageRulesPhotoImage.freeSpaceToFrameStart- ImageRulesPhotoImage.freeSpaceToFrameStart;
	}

	private int getFreeSpaceToPictureBorder(){
		return ImageRulesPhotoImage.freeSpaceToFrameStart;
	}

	private boolean framePositionIsBottomOfPicture( ) {
		return
			logo.getLogoPosition().equals( LogoPosition.TOP_LEFT ) || logo.getLogoPosition().equals( LogoPosition.TOP_RIGHT );
	}

	private void placeHeader(){
		setColorHeader();
		setFontHeader();
		drawHeaderOnPhoto();
	}

	private void placeSubheader( ){
		setColorSubheader();
		setFontSubheader();
		drawSubheaderOnPhoto();
	}

	private void drawHeaderOnPhoto(){
		int xCoordinate = ImageRulesPhotoImage.freeSpaceToFrameStart + ImageRulesPhotoImage.freeSpaceToHeadlineStartFromFrame;
		yCoordinateHeaders = yCoordinateFrameStart + getFreeYSpaceForFrameInFrame() + getFreeSpaceToPictureBorder()
			+ getFreeSpaceToPictureBorder();

		for (int i = 0; i < finalTextSpecificationHeader.getTextPerLine().size(); i++){
			g.drawString( finalTextSpecificationHeader.getTextPerLine().get( i ), xCoordinate, yCoordinateHeaders );
			yCoordinateHeaders = yCoordinateHeaders + finalTextSpecificationHeader.getTextSize();
		}
	}

	private void drawSubheaderOnPhoto(){
		int xCoordinate = ImageRulesPhotoImage.freeSpaceToFrameStart + ImageRulesPhotoImage.freeSpaceToHeadlineStartFromFrame;
		yCoordinateHeaders = yCoordinateHeaders + getFreeSpaceBetweenHeadlines();

		for (int i = 0; i < finalTextSpecificationSubheader.getTextPerLine().size(); i++){
			g.drawString( finalTextSpecificationSubheader.getTextPerLine().get( i ), xCoordinate, yCoordinateHeaders );
			yCoordinateHeaders = yCoordinateHeaders + finalTextSpecificationSubheader.getTextSize();
		}
	}


	private void adaptHeaderText( )
	{
		setHeaderTextConfiguration();
		setFontHeader();
	}

	private void setHeaderTextConfiguration(){
		finalTextSpecificationHeader = new AdaptTextPhotoImage(componentPropertiesConfig).adaptHeaderConfig( data.getHeader( ) );
	}

	private void adaptSubheaderText(){
		setSubheaderTextConfiguration();
		setFontSubheader();
	}

	private void setSubheaderTextConfiguration(){
		finalTextSpecificationSubheader = new AdaptTextPhotoImage( componentPropertiesConfig ).adaptSubheaderConfig( data.getSubheader( ) );
	}

	private void setColorHeader(){
		g.setColor( colorConfig.getHeaderColor() );
	}

	private void setColorSubheader(){
		g.setColor( colorConfig.getSubheaderColor() );
	}

	private void setFontSubheader(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getSubheaderFontStyle(),
			finalTextSpecificationSubheader.getTextSize() ));
	}

	private void setFontHeader(){
		g.setFont( new Font( componentPropertiesConfig.getFont(), componentPropertiesConfig.getHeaderFontStyle(),
			finalTextSpecificationHeader.getTextSize()));
	}

	private void placeLogo( ) throws IOException {
		logo = new Logo.Builder()
			.withImage( image )
			.andFrameColor( colorConfig.getLogoColor() )
			.andLogoColor( colorConfig.getLogoColor() )
			.andLogoPosition( componentPropertiesConfig.getLogoPosition() )
				.generate();

		drawLogoOnPhoto();
	}

	private void drawLogoOnPhoto(){
		g.drawImage( logo.getLogoImage(), logo.getXCoordinate(), logo.getYCoordinate(), logo.getLogoWidth(),
			logo.getLogoHeight(), null  );
	}

	public static class Builder
	{
		private GeneratingPicture generatingPicture;

		public Builder()
		{
			this.generatingPicture = new GeneratingPicture();
		}
		public Builder1 withImportData( PhotoImageModel photoImageModel )
		{
			this.generatingPicture.setData( photoImageModel );
			return new Builder1( this.generatingPicture );
		}
	}

	public static class Builder1
	{
		private GeneratingPicture generatingPicture;

		public Builder1( GeneratingPicture generatingPicture )
		{
			this.generatingPicture = generatingPicture;
		}

		public Builder2 andColorConfig( ColorConfig colorConfig )
		{
			this.generatingPicture.setColorConfig( colorConfig );
			return new Builder2( this.generatingPicture );
		}
	}

	public static class Builder2
	{
		private GeneratingPicture generatingPicture;

		public Builder2( GeneratingPicture generatingPicture )
		{
			this.generatingPicture = generatingPicture;
		}

		public Builder3 andComponentPropertiesConfig( ComponentPropertiesConfig componentPropertiesConfig )
		{
			this.generatingPicture.setComponentPropertiesConfig( componentPropertiesConfig );
			return new Builder3( this.generatingPicture );
		}
	}

	public static class Builder3
	{
		private GeneratingPicture generatingPicture;

		public Builder3( GeneratingPicture generatingPicture )
		{
			this.generatingPicture = generatingPicture;
		}

		public BufferedImage generate( ) throws IOException
		{
			return this.generatingPicture.generatePhoto(  );
		}
	}
}

