package de.fhws.fiw.instagram.fiwRecommends;

import de.fhws.fiw.instagram.utils.AbstractInstagramModel;

import java.awt.*;

public class FiwRecommendsModel extends AbstractInstagramModel
{
	private String headline;
	private Image image;
	private String outputPath;

	public FiwRecommendsModel( )
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
