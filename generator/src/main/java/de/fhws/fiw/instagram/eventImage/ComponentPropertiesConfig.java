package de.fhws.fiw.instagram.eventImage;


public class ComponentPropertiesConfig
{
	private  String name;
	private  String font;
	private  int frameXCoordinate;
	private  int frameYCoordinate;
	private  int fillframeXCoordinate;
	private  int fillframeYCoordinate;
	private int xCoordinateText;
	private  int hostTextSize;
	private int hostFontStyle;
	private  int headerMinSize;
	private  int headerMaxSize;
	private int headerFontStyle;
	private  int subheaderMinSize;
	private  int subheaderMaxSize;
	private int subheaderFontStyle;
	private  int contentMinSize;
	private  int contentMaxSize;
	private int contentFontStyle;
	private  int timeAndLocationTextSize;
	private  int timeAndLocationYCoordinate;
	private int timeAndLocationFontStyle;

	public ComponentPropertiesConfig( String name, String font, int frameXCoordinate, int frameYCoordinate,
		int fillframeXCoordinate, int fillframeYCoordinate, int xCoordinateText, int hostTextSize,
		 int hostFontStyle,  int headerMinSize, int headerMaxSize, int headerFontStyle,
		int subheaderMinSize, int subheaderMaxSize, int subheaderFontStyle, int contentMinSize, int contentMaxSize,
		int contentFontStyle, int timeAndLocationTextSize, int timeAndLocationYCoordinate, int timeAndLocationFontStyle )
	{
		this.name = name;
		this.font = font;
		this.frameXCoordinate = frameXCoordinate;
		this.frameYCoordinate = frameYCoordinate;
		this.fillframeXCoordinate = fillframeXCoordinate;
		this.fillframeYCoordinate = fillframeYCoordinate;
		this.xCoordinateText = xCoordinateText;
		this.hostTextSize = hostTextSize;
		this.hostFontStyle = hostFontStyle;
		this.headerMaxSize = headerMaxSize;
		this.headerMinSize = headerMinSize;
		this.headerFontStyle = headerFontStyle;
		this.subheaderMinSize = subheaderMinSize;
		this.subheaderMaxSize = subheaderMaxSize;
		this.subheaderFontStyle = subheaderFontStyle;
		this.contentMinSize = contentMinSize;
		this.contentMaxSize = contentMaxSize;
		this.contentFontStyle = contentFontStyle;
		this.timeAndLocationTextSize = timeAndLocationTextSize;
		this.timeAndLocationYCoordinate = timeAndLocationYCoordinate;
		this.timeAndLocationFontStyle = timeAndLocationFontStyle;
	}

	public String getName( )
	{
		return name;
	}

	public String getFont( )
	{
		return font;
	}

	public int getFrameXCoordinate( )
	{
		return frameXCoordinate;
	}

	public int getFrameYCoordinate( )
	{
		return frameYCoordinate;
	}

	public int getFillframeXCoordinate( )
	{
		return fillframeXCoordinate;
	}

	public int getTimeAndLocationFontStyle( )
	{
		return timeAndLocationFontStyle;
	}

	public int getFillframeYCoordinate( )
	{
		return fillframeYCoordinate;
	}

	public int getHostTextSize( )
	{
		return hostTextSize;
	}

	public int getHostFontStyle( )
	{
		return hostFontStyle;
	}

	public int getHeaderMinSize( )
	{
		return headerMinSize;
	}

	public int getHeaderMaxSize( )
	{
		return headerMaxSize;
	}

	public int getHeaderFontStyle( )
	{
		return headerFontStyle;
	}

	public int getSubheaderMinSize( )
	{
		return subheaderMinSize;
	}

	public int getSubheaderMaxSize( )
	{
		return subheaderMaxSize;
	}

	public int getSubheaderFontStyle( )
	{
		return subheaderFontStyle;
	}

	public int getContentMinSize( )
	{
		return contentMinSize;
	}

	public int getContentMaxSize( )
	{
		return contentMaxSize;
	}

	public int getContentFontStyle( )
	{
		return contentFontStyle;
	}

	public int getTimeAndLocationTextSize( )
	{
		return timeAndLocationTextSize;
	}

	public int getTimeAndLocationYCoordinate( )
	{
		return timeAndLocationYCoordinate;
	}
}
