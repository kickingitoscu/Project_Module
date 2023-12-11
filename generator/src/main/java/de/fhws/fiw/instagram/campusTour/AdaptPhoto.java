package de.fhws.fiw.instagram.campusTour;

import de.fhws.fiw.instagram.utils.ImageRules;
import java.awt.*;
import java.awt.image.BufferedImage;


public class AdaptPhoto
{
	private int targetWidth = ImageRules.pictureWidth;
	private int targetHeight = ImageRules.pictureHeight;
	private Image importedImage;
	private BufferedImage image;
	private Graphics2D g;

	public AdaptPhoto(){
		configureGraphics();
	}

	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	public Image getScaledImage(Image importedImage)
	{
		this.importedImage = importedImage;
		customizeImage();
		return image;
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
		importedImage = scaledImage;
	}

	private double getImageScale( )
	{
		// Gibt die Scalierung für die kleinere Seite des Bildes zurück
		double scalex = ( double ) targetWidth / importedImage.getWidth( null);
		double scaley = ( double ) targetHeight / importedImage.getHeight( null);
		return Math.max( scalex, scaley );
	}

	private void cutImage( ){
		if( imageHeightIsTargetHeight( ) ){
			setImageForPhotoIsHorizontalFormat();
		}
		else{
			setImageForPhotoIsUpright();
		}
	}

	private void setImageForPhotoIsHorizontalFormat(){
		int surplus =  importedImage.getWidth( null )-targetWidth;
		g.drawImage( importedImage, 0-(surplus/2),0,null  );
	}

	private void setImageForPhotoIsUpright(){
		int surplus =  importedImage.getHeight( null )-targetHeight;
		g.drawImage( importedImage, 0,0-(surplus/2),null  );
	}

	private boolean imageHeightIsTargetHeight( )
	{
		return importedImage.getHeight(null)==targetHeight;
	}

}
