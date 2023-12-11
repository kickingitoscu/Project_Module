package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.ImageRules;
import java.awt.*;

public class AdaptPhoto
{
	private int targetWidth = ImageRules.pictureWidth;
	private int targetHeight = ImageRules.pictureHeight;
	private ImageConfiguration imageConfiguration;
	private Image importedImage;
	private ComponentPropertiesConfig componentPropertiesConfig;

	public AdaptPhoto(){
		imageConfiguration = new ImageConfiguration();
	}

	public ImageConfiguration getImageConfiguration(Image importedImage, ComponentPropertiesConfig componentPropertiesConfig)
	{
		this.importedImage = importedImage;
		this.componentPropertiesConfig = componentPropertiesConfig;

		customizeImage();
		return imageConfiguration;
	}

	private void customizeImage( )
	{
		scaleImage( );
		cutImage( );
	}

	private void scaleImage()
	{
		//Skaliert das Bild mit der kleineren Seite auf die Zielseitengröße
		double scale = getImageScale( );
		Image scaledImage =
			importedImage.getScaledInstance((int) (importedImage.getWidth( null ) * scale),
				(int) (importedImage.getHeight( null ) * scale), Image.SCALE_SMOOTH);
		imageConfiguration.setImage( scaledImage );
	}

	private double getImageScale( )
	{
		// Gibt die Scalierung für die kleinere Seite des Bildes zurück
		double scalex = ( double ) targetWidth / importedImage.getWidth( null);
		double scaley = ( double ) targetHeight / importedImage.getHeight( null);
		return Math.max( scalex, scaley );
	}

	private void cutImage( ){
		setXYCooridinatesForImageConfiguration();

		if( imageHeightIsTargetHeight( ) ){
			int surplus =  imageConfiguration.getImage().getWidth( null )-targetWidth;

			switch ( componentPropertiesConfig.getCutPositionPicture() ){
			case CENTER: imageConfiguration.setXCoordinate( imageConfiguration.getXCoordinate( )-(surplus/2) );
				break;
			case BEGINNING: //X-Koordinate bleibt bei 0
				break;
			case END:  imageConfiguration.setXCoordinate( imageConfiguration.getXCoordinate( )-surplus ) ;
				break;
			default: imageConfiguration.setXCoordinate( imageConfiguration.getXCoordinate( )-(surplus/2) );
			}
		}
		else{
			int surplus =  imageConfiguration.getImage().getHeight( null )-targetHeight;

			switch ( componentPropertiesConfig.getCutPositionPicture() ){
			case CENTER: imageConfiguration.setYCoordinate( imageConfiguration.getYCoordinate( )-(surplus/2) );
				break;
			case BEGINNING: // Y-Koordinate bleibt bei 0
				break;
			case END: imageConfiguration.setYCoordinate( imageConfiguration.getYCoordinate( )-surplus);
				break;
			default: imageConfiguration.setYCoordinate( imageConfiguration.getYCoordinate( )-(surplus/2) );
			}
		}
	}

	private boolean imageHeightIsTargetHeight( )
	{
		return imageConfiguration.getImage().getHeight(null)==targetHeight;
	}

	private void setXYCooridinatesForImageConfiguration(){
		imageConfiguration.setYCoordinate( 0 );
		imageConfiguration.setXCoordinate( 0 );
	}
}
