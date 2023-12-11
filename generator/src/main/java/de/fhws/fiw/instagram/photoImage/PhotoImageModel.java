package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.AbstractInstagramModel;

import java.awt.*;

public class PhotoImageModel extends AbstractInstagramModel
{
	private String header;
	private String subheader;
	private Image importedImage;
	private String outputPath;


	public PhotoImageModel(){}

	public void setHeader( String header )
	{
		this.header = header;
	}

	public void setSubheader( String subheader )
	{
		this.subheader = subheader;
	}

	public void setImportedImage( Image importedImage )
	{
		this.importedImage = importedImage;
	}

	public String getHeader( )
	{
		return header;
	}

	public String getSubheader( )
	{
		return subheader;
	}

	public Image getImportedImage( )
	{
		return importedImage;
	}

	public String getOutputPath( )
	{
		return outputPath;
	}

	public void setOutputPath( String outputPath )
	{
		this.outputPath = outputPath;
	}
}
