package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.LogoColor;
import de.fhws.fiw.instagram.utils.LogoPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logo
{
	private int xCoordinate, yCoordinate;
	private LogoPosition logoPosition;
	private LogoColor logoColor;
	private Image imageLogo;
	private Image backgroundPhoto;
	private LogoColor inputLogoColor;


	private Logo(){}

	public int getXCoordinate( )
	{
		return xCoordinate;
	}

	public int getYCoordinate( )
	{
		return yCoordinate;
	}

	public LogoPosition getLogoPosition( )
	{
		return logoPosition;
	}

	public int getLogoHeight( )
	{
		return ImageRules.logoHeight;
	}

	public int getLogoWidth( )
	{
		return ImageRules.logoWidth;
	}

	public Image getLogoImage(){
		return imageLogo;
	}


	private Logo generateLogo( ) throws IOException
	{
		setLogoOnRightPosition( );
		selectLogoColorConsiderBackgroundColor();
		selectLogoInCorrectColor();
		return this;
	}

	private void setLogoOnRightPosition(){
		switch ( logoPosition ){
		case TOP_LEFT: setXCoordinate( ImageRulesPhotoImage.freeSpaceToTextStart ); setYCoordinate( ImageRulesPhotoImage.freeSpaceToTextStart);
			break;
		case BOTTOM_RIGHT: setXCoordinate( getXCoordinateIfLogoPositionIsRight()); setYCoordinate(getYCoordinateIfLogoPositionIsBottom());
			break;
		case TOP_RIGHT: setXCoordinate( getXCoordinateIfLogoPositionIsRight( ) ); setYCoordinate( ImageRulesPhotoImage.freeSpaceToTextStart);
			break;
		case BOTTOM_LEFT: setXCoordinate( ImageRulesPhotoImage.freeSpaceToTextStart); setYCoordinate(getYCoordinateIfLogoPositionIsBottom());
		}
	}

	private void selectLogoColorConsiderBackgroundColor(){

		double brightnessColorOfBackgroundOfLogoPosition = getBrightnessColorOfBackgroundOfLogoPosition( getAverageColorOfBackgroundOfLogoPosition() ) ;
		//brightnessOrange = ca. 150

		//System.out.println(brightnessColorOfBackgroundOfLogoPosition );

		if (brightnessColorOfBackgroundOfLogoPosition > 150){
			if(inputLogoColor.equals( LogoColor.ORANGE )){
				logoColor = inputLogoColor;
			}
			else {
				logoColor = LogoColor.BLACK;
			}
		}
		else{
			logoColor = LogoColor.WHITE;
		}
	}

	private double getBrightnessColorOfBackgroundOfLogoPosition(Color averageColorOfBackgroundOfLogoPosition){
		return Math.sqrt(
			0.299*Math.pow( averageColorOfBackgroundOfLogoPosition.getRed(),2 )
				+0.587*Math.pow( averageColorOfBackgroundOfLogoPosition.getGreen( ), 2)
				+0.114*Math.pow( averageColorOfBackgroundOfLogoPosition.getBlue(),2 )) ;
	}

	private Color getAverageColorOfBackgroundOfLogoPosition(){
		return averageColor( (BufferedImage ) backgroundPhoto, getXCoordinateForLogoColor(),
			getYCoordinateForLogoColor( ), getWidthOfPlaceForLogo( ), getHeightOfPlaceForLogo( ) );
	}

	private Color averageColor( BufferedImage bi, int x0, int y0, int w, int h) {
		int x1 = x0 + w;
		int y1 = y0 + h;
		int sumr = 0, sumg = 0, sumb = 0;
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				Color pixel = new Color(bi.getRGB(x, y));
				sumr += pixel.getRed();
				sumg += pixel.getGreen();
				sumb += pixel.getBlue();
			}
		}
		int num = w * h;

		return new Color(sumr / num, sumg / num, sumb / num);
	}

	private void selectLogoInCorrectColor(  ) throws IOException
	{
		String logoPath = "";
		switch ( logoColor ){
			case BLACK: logoPath = LogoPath.logoPathBlack;
				break;
			case ORANGE: logoPath = LogoPath.logoPathOrange;
				break;
			case WHITE: logoPath = LogoPath.logoPathWhite;
		}

		imageLogo = ImageIO.read( getClass().getResource(logoPath) );
	}

	private int getYCoordinateIfLogoPositionIsBottom(){
		return ImageRules.pictureHeight - ImageRulesPhotoImage.freeSpaceToTextStart - getLogoHeight();
	}

	private int getXCoordinateIfLogoPositionIsRight( ) {
		return ImageRules.pictureWidth- ImageRulesPhotoImage.freeSpaceToTextStart-getLogoWidth();
	}

	private int getHeightOfPlaceForLogo( ) {
		return ImageRules.logoHeight + ImageRulesPhotoImage.freeSpaceToFrameStart;
	}

	private int getWidthOfPlaceForLogo( ) {
		return ImageRules.logoWidth + ImageRulesPhotoImage.freeSpaceToTextStart;
	}

	private int getYCoordinateForLogoColor( ) {
		return this.yCoordinate- ImageRulesPhotoImage.freeSpaceToFrameStart;
	}

	private int getXCoordinateForLogoColor(){
		return this.xCoordinate - ImageRulesPhotoImage.freeSpaceToFrameStart;
	}

	private void setBackgroundPhoto( Image backgroundPhoto )
	{
		this.backgroundPhoto = backgroundPhoto;
	}

	private void setXCoordinate( int xCoordinate )
	{
		this.xCoordinate = xCoordinate;
	}

	private void setYCoordinate( int yCoordinate )
	{
		this.yCoordinate = yCoordinate;
	}

	private void setLogoPosition( LogoPosition logoPosition )
	{
		this.logoPosition = logoPosition;
	}

	private void setLogoColor( LogoColor logoColor )
	{
		this.logoColor = logoColor;
	}

	private void setInputLogoColor( LogoColor inputLogoColor ) {
		this.inputLogoColor = inputLogoColor;
	}


	public static class Builder
	{
		private Logo logo;

		public Builder()
		{
			this.logo = new Logo();
		}
		public Logo.Builder1 withImage( Image image )
		{
			this.logo.setBackgroundPhoto( image );
			return new Logo.Builder1( this.logo );
		}
	}

	public static class Builder1
	{
		private Logo logo;

		public Builder1(  Logo logo )
		{
			this.logo = logo;
		}

		public Logo.Builder2 andFrameColor( LogoColor frameColor )
		{
			this.logo.setInputLogoColor( frameColor );
			return new Logo.Builder2( this.logo );
		}
	}

	public static class Builder2
	{
		private Logo logo;

		public Builder2( Logo logo  )
		{
			this.logo = logo;
		}

		public Logo.Builder3 andLogoColor( LogoColor logoColor )
		{
			this.logo.setLogoColor(logoColor);
			return new Logo.Builder3( this.logo );
		}
	}

	public static class Builder3
	{
		private Logo logo;

		public Builder3( Logo logo  )
		{
			this.logo = logo;
		}

		public Logo.Builder4 andLogoPosition( LogoPosition logoPosition )
		{
			this.logo.setLogoPosition( logoPosition );
			return new Logo.Builder4( this.logo );
		}
	}

	public static class Builder4
	{
		private Logo logo;

		public Builder4( Logo logo  )
		{
			this.logo = logo;
		}

		public Logo generate( ) throws IOException
		{
			return this.logo.generateLogo();
		}
	}

}
