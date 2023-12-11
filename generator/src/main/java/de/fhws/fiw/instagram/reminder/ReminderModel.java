package de.fhws.fiw.instagram.reminder;

import java.awt.*;

public class ReminderModel
{
	private String text;
	private Image photo;
	private String outputPath;


	public ReminderModel(){}

	public String getText( )
	{
		return text;
	}

	public void setText( String text )
	{
		this.text = text;
	}

	public Image getPhoto( )
	{
		return photo;
	}

	public void setPhoto( Image photo )
	{
		this.photo = photo;
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
