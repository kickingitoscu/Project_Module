package de.fhws.fiw.instagram.information;

import de.fhws.fiw.instagram.utils.AbstractInstagramLoader;

import java.util.Properties;

public class InformationLoader extends AbstractInstagramLoader<InformationModel>
{
	@Override protected InformationModel createEmptyModel( )
	{
		return new InformationModel( );
	}

	@Override protected void putDataInModel( InformationModel model, Properties properties )
	{
		model.setText( properties.getProperty( "Text" ) );
		model.setImage( readImage( properties.getProperty( "Image" ) ) );
		model.setOutputPath( properties.getProperty( "Outputpath" ) );
	}
}