package de.fhws.fiw.instagram.utils;

public class TextSpecificationInput
{
	private String inputText;

	private int minTextSize;
	private int maxTextSize;
	private int maxLinesOfText;
	private int fontStyle;
	private String font;
	private int freeSpaceForText;

	public TextSpecificationInput(){}

	public int getFreeSpaceForText( )
	{
		return freeSpaceForText;
	}

	public void setFreeSpaceForText( int freeSpaceForText )
	{
		this.freeSpaceForText = freeSpaceForText;
	}

	public String getInputText( )
	{
		return inputText;
	}

	public void setInputText( String inputText )
	{
		this.inputText = inputText;
	}

	public int getMinTextSize( )
	{
		return minTextSize;
	}

	public void setMinTextSize( int minTextSize )
	{
		this.minTextSize = minTextSize;
	}

	public int getMaxTextSize( )
	{
		return maxTextSize;
	}

	public void setMaxTextSize( int maxTextSize )
	{
		this.maxTextSize = maxTextSize;
	}

	public int getMaxLinesOfText( )
	{
		return maxLinesOfText;
	}

	public void setMaxLinesOfText( int maxLinesOfText )
	{
		this.maxLinesOfText = maxLinesOfText;
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
