package de.fhws.fiw.instagram.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public abstract class AbstractInstagramLoader<M extends AbstractInstagramModel>
{
	public M loadByFileName( final String fileName )
	{
		final M model = createEmptyModel( );

		try (InputStream input = new FileInputStream( fileName ))
		{
			final Properties properties = new Properties( );
			final InputStreamReader isr = new InputStreamReader( input, Charset.forName( "UTF-8" ) );

			properties.load( isr );

			putDataInModel( model, properties );
		}
		catch ( IOException ex )
		{
			ex.printStackTrace( );
		}

		return model;
	}

	protected abstract M createEmptyModel( );

	protected abstract void putDataInModel( final M model, final Properties properties );

	protected BufferedImage readImage( String imagePath )
	{
		BufferedImage image = null;

		try
		{
			image = ImageIO.read( new File(imagePath) );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}

		return image;
	}
}