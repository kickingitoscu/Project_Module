package de.fhws.fiw.instagram.information;

import de.fhws.fiw.instagram.utils.AbstractInstagramModel;

import java.awt.*;

public class InformationModel extends AbstractInstagramModel
{
	private String text;
	private Image image;
	private String outputPath;


	public InformationModel(){}

	public String getText( )
	{
		return text;
	}

	public void setText( String text )
	{
		this.text = text;
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
