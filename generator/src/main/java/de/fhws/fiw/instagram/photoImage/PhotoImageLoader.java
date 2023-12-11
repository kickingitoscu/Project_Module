package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.AbstractInstagramLoader;

import java.util.Properties;

public class PhotoImageLoader extends AbstractInstagramLoader<PhotoImageModel>
{
	@Override protected PhotoImageModel createEmptyModel( )
	{
		return new PhotoImageModel( );
	}

	@Override protected void putDataInModel( PhotoImageModel model, Properties properties )
	{
		model.setHeader( properties.getProperty( "Header" ) );
		model.setSubheader( properties.getProperty( "Subheader" )  );
		model.setImportedImage( readImage( properties.getProperty( "Image" ) ) );
		model.setOutputPath( properties.getProperty( "Outputpath" ) );
	}
}