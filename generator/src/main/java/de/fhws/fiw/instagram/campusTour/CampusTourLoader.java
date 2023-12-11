package de.fhws.fiw.instagram.campusTour;

import de.fhws.fiw.instagram.utils.AbstractInstagramLoader;

import java.util.Properties;

public class CampusTourLoader extends AbstractInstagramLoader<CampusTourModel>
{
	@Override protected CampusTourModel createEmptyModel( )
	{
		return new CampusTourModel( );
	}

	@Override protected void putDataInModel( CampusTourModel model, Properties properties )
	{
		model.setOutputPath( properties.getProperty( "Outputpath" ) );
		model.setPhotoFirstPage(readImage( properties.getProperty( "Image1" ) )  );
		model.setPhotoSecondPage(readImage( properties.getProperty( "Image2" ) )  );
		model.setPhotoThirdPage(readImage( properties.getProperty( "Image3" ) )  );
		model.setTitleFirstPage( properties.getProperty( "Title1" ) );
		model.setTitleSecondPage( properties.getProperty( "Title2" ) );
		model.setTitleThirdPage( properties.getProperty( "Title3" ) );
	}
}