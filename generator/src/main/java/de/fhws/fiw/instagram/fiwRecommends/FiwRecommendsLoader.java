package de.fhws.fiw.instagram.fiwRecommends;

import de.fhws.fiw.instagram.utils.AbstractInstagramLoader;

import java.util.Properties;

public class FiwRecommendsLoader extends AbstractInstagramLoader<FiwRecommendsModel>
{
	@Override protected FiwRecommendsModel createEmptyModel( )
	{
		return new FiwRecommendsModel( );
	}

	@Override protected void putDataInModel( FiwRecommendsModel model, Properties properties )
	{
		model.setHeadline( properties.getProperty( "Headline" ) );
		model.setImage( readImage( properties.getProperty( "Image" ) ) );
		model.setOutputPath( properties.getProperty( "Outputpath" ) );
	}
}