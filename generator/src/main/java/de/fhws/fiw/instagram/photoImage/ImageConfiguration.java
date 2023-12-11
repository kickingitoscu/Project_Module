package de.fhws.fiw.instagram.photoImage;

import java.awt.*;

public class ImageConfiguration
{
	private Image image;
	private int xCoordinate, yCoordinate;

	public Image getImage( )
	{
		return image;
	}

	public void setImage( Image image )
	{
		this.image = image;
	}

	public int getXCoordinate( )
	{
		return xCoordinate;
	}

	public void setXCoordinate( int xCoordinate )
	{
		this.xCoordinate = xCoordinate;
	}

	public int getYCoordinate( )
	{
		return yCoordinate;
	}

	public void setYCoordinate( int yCoordinate )
	{
		this.yCoordinate = yCoordinate;
	}
}
