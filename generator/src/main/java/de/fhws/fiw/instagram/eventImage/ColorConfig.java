package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.LogoColor;

import java.awt.*;

public class ColorConfig
{
	private final String name;
	private final Color headerColor;
	private final Color subheaderColor;
	private final Color hostColor;
	private final Color contentColor;
	private final Color timeANDloctionColor;
	private final Color lineColor;
	private final LogoColor logoColor;
	private final Color backgroundColor;
	private final Color frameColor;
	private final Color fillFrameColor;

	public ColorConfig( String name,Color backgroundColor, Color frameColor, Color fillFrameColor, Color headerColor,
		Color subheaderColor, Color hostColor, Color contentColor, Color timeANDloctionColor,
		Color lineColor, LogoColor logoColor )
	{
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;
		this.fillFrameColor = fillFrameColor;
		this.headerColor = headerColor;
		this.subheaderColor = subheaderColor;
		this.hostColor = hostColor;
		this.contentColor = contentColor;
		this.timeANDloctionColor = timeANDloctionColor;
		this.lineColor = lineColor;
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

	public Color getHostColor( )
	{
		return hostColor;
	}

	public Color getContentColor( )
	{
		return contentColor;
	}

	public Color getTimeANDloctionColor( )
	{
		return timeANDloctionColor;
	}

	public Color getLineColor( )
	{
		return lineColor;
	}

	public LogoColor getLogoColor( )
	{
		return logoColor;
	}

	public Color getBackgroundColor( )
	{
		return backgroundColor;
	}

	public Color getFrameColor( )
	{
		return frameColor;
	}

	public Color getFillFrameColor( )
	{
		return fillFrameColor;
	}
}
