package de.fhws.fiw.instagram.utils;

import java.util.List;

public class TextSpecificationOutput
{
	private int textSize;
	private List<String> textPerLine;
	private int fontStyle;
	private String font;


	public int getTextSize( )
	{
		return textSize;
	}

	public void setTextSize( int textSize )
	{
		this.textSize = textSize;
	}

	public List<String> getTextPerLine( )
	{
		return textPerLine;
	}

	public void setTextPerLine( List<String> textPerLine )
	{
		this.textPerLine = textPerLine;
	}

	public int getFontStyle( )
	{
		return fontStyle;
	}

	public void setFontStyle( int fontStyle )
	{
		this.fontStyle = fontStyle;
	}

	public String getFont( )
	{
		return font;
	}

	public void setFont( String font )
	{
		this.font = font;
	}
}
