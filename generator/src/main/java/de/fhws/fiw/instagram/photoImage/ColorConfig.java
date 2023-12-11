package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.LogoColor;

import java.awt.*;

public class ColorConfig
{
	private String name;
	private Color headerColor;
	private Color subheaderColor;
	private LogoColor logoColor;
	private Color frameColor;


	public ColorConfig (String name, Color frameColor, Color headerColor, Color subheaderColor, LogoColor logoColor ){
		this.name = name;
		this.frameColor = frameColor;
		this.headerColor = headerColor;
		this.subheaderColor = subheaderColor;
		this.logoColor = logoColor;
	}

	public String getName( )
	{
		return name;
	}

	public Color getHeaderColor( )
	{
		return headerColor;
	}

	public Color getSubheaderColor( )
	{
		return subheaderColor;
	}

	public LogoColor getLogoColor( )
	{
		return logoColor;
	}

	public Color getFrameColor( )
	{
		return frameColor;
	}
}
