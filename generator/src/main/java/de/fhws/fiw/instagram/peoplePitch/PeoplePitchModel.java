package de.fhws.fiw.instagram.peoplePitch;

import java.awt.*;

public class PeoplePitchModel
{
	private String name;
	private String area;
	private String since;
	private String favoriteTask;
	private String firstTaskInTheMorning;
	private String outputPath;
	private String position;
	private Image photo;

	public PeoplePitchModel(){}

	public String getPosition( )
	{
		return position;
	}

	public void setPosition( String position )
	{
		this.position = position;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getArea( )
	{
		return area;
	}

	public void setArea( String area )
	{
		this.area = area;
	}

	public String getSince( )
	{
		return since;
	}

	public void setSince( String since )
	{
		this.since = since;
	}

	public String getFavoriteTask( )
	{
		return favoriteTask;
	}

	public void setFavoriteTask( String favoriteTask )
	{
		this.favoriteTask = favoriteTask;
	}

	public String getFirstTaskInTheMorning( )
	{
		return firstTaskInTheMorning;
	}

	public void setFirstTaskInTheMorning( String firstTaskInTheMorning )
	{
		this.firstTaskInTheMorning = firstTaskInTheMorning;
	}

	public String getOutputPath( )
	{
		return outputPath;
	}

	public void setOutputPath( String outputPath )
	{
		this.outputPath = outputPath;
	}

	public Image getPhoto( )
	{
		return photo;
	}

	public void setPhoto( Image photo )
	{
		this.photo = photo;
	}
}
