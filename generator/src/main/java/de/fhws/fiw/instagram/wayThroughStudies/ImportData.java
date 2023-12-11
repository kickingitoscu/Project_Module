package de.fhws.fiw.instagram.wayThroughStudies;

import java.awt.*;
import java.util.List;

public class ImportData
{
	private String headline;
	private String footer;
	private Image image;
	private String outputPath;

	public ImportData( )
	{
	}

	public String getHeadline( )
	{
		return headline;
	}

	public void setHeadline( String headline )
	{
		this.headline = headline;
	}

	public String getFooter( )
	{
		return footer;
	}

	public void setFooter( String footer )
	{
		this.footer = footer;
	}

	public Image getImage( )
	{
		return image;
	}

	public void setImage( Image image )
	{
		this.image = image;
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
