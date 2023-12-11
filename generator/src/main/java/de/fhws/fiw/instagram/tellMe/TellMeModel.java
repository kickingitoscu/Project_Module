package de.fhws.fiw.instagram.tellMe;

import java.awt.*;
import java.util.List;

public class TellMeModel
{
	private String name;
	private String position;
	private String quote;
	private List<String> aboutPerson;
	private String additionalQuestionAnswer;
	private String additionalQuestion;
	private Image photo;
	private String outputPath;
	private String speech;


	public TellMeModel(){}

	public String getSpeech( )
	{
		return speech;
	}

	public void setSpeech( String speech )
	{
		this.speech = speech;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getPosition( )
	{
		return position;
	}

	public void setPosition( String position )
	{
		this.position = position;
	}

	public String getQuote( )
	{
		return quote;
	}

	public void setQuote( String quote )
	{
		this.quote = quote;
	}

	public List<String> getAboutPerson( )
	{
		return aboutPerson;
	}

	public void setAboutPerson( List<String> aboutPerson )
	{
		this.aboutPerson = aboutPerson;
	}

	public String getAdditionalQuestionAnswer( )
	{
		return additionalQuestionAnswer;
	}

	public void setAdditionalQuestionAnswer( String additionalQuestionAnswer )
	{
		this.additionalQuestionAnswer = additionalQuestionAnswer;
	}

	public String getAdditionalQuestion( )
	{
		return additionalQuestion;
	}

	public void setAdditionalQuestion( String additionalQuestion )
	{
		this.additionalQuestion = additionalQuestion;
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
