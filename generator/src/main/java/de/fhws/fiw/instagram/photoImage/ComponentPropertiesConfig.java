package de.fhws.fiw.instagram.photoImage;

public class ComponentPropertiesConfig
{
	private String name;
	private  String font;
	private int headerFontStyle;
	private int subheaderFontStyle;
	private CutPositionPicture cutPositionPicture;
	private LogoPosition logoPosition;


	public ComponentPropertiesConfig( String name, String font, int headerFontStyle, int subheaderFontStyle,
		LogoPosition logoPosition, CutPositionPicture cutPositionPicture)
	{
		this.name = name;
		this.font = font;
		this.headerFontStyle = headerFontStyle;
		this.subheaderFontStyle = subheaderFontStyle;
		this.cutPositionPicture = cutPositionPicture;
		this.logoPosition = logoPosition;
	}

	public String getName( )
	{
		return name;
	}

	public CutPositionPicture getCutPositionPicture( )
	{
		return cutPositionPicture;
	}

	public LogoPosition getLogoPosition( )
	{
		return logoPosition;
	}

	public String getFont( )
	{
		return font;
	}

	public int getHeaderFontStyle( )
	{
		return headerFontStyle;
	}

	public int getSubheaderFontStyle( )
	{
		return subheaderFontStyle;
	}
}
