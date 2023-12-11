package de.fhws.fiw.instagram.eventImage;

import java.util.List;

public class EventImageModel
{
	private  String header;
	private  String subheader;
	private  String location;
	private  String time;
	private  String host;
	private  List<String> content;
	private  String outputPath;

	public EventImageModel(){}


	public EventImageModel( String header, String subheader, String location, String time, String host, List<String> content )
	{
		this.header = header;
		this.subheader = subheader;
		this.location = location;
		this.time = time;
		this.host = host;
		this.content = content;
	}

	public String getHeader( )
	{
		return header;
	}

	public void setHeader( String header )
	{
		this.header = header;
	}

	public String getSubheader( )
	{
		return subheader;
	}

	public void setSubheader( String subheader )
	{
		this.subheader = subheader;
	}

	public String getLocation( )
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public String getTime( )
	{
		return time;
	}

	public void setTime( String time )
	{
		this.time = time;
	}

	public String getHost( )
	{
		return host;
	}

	public void setHost( String host )
	{
		this.host = host;
	}

	public List<String> getContent( )
	{
		return content;
	}

	public void setContent( List<String> content )
	{
		this.content = content;
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
