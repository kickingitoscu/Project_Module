package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.LogoColor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ColorData

	/*
	Background Color:
		0: Black
		1: White
		2: Dark Grey
		3: Green
		4: Orange
	Color Header:
		0: Black
		1: White
		2: Dark Grey
		3: Green
		4: Orange
	Color Subheader:
		0: Black
		1: White
		2: Dark Grey
		3: Green
		4: Orange
	 */
{
	//(String name, Color fillFrameColor, Color headerColor, Color subheaderColor, LogoColor logoColor )
	private static ColorConfig Green_Black = new ColorConfig( "3_0_1",
		new Color( 143,212,0,230 ), Color.BLACK, Color.white, LogoColor.BLACK );

	private static ColorConfig Green_Black_Grey = new ColorConfig( "3_0_0",
		new Color( 143,212,0,230 ), Color.BLACK, Color.BLACK, LogoColor.BLACK );

	private static ColorConfig Green_White_White = new ColorConfig( "3_1_1",
		new Color( 143,212,0,230 ), Color.white, Color.white, LogoColor.WHITE );

	private static ColorConfig Black_Green_white = new ColorConfig( "0_3_1",
		new Color( 0,0,0,200), new FHWS_Color().green, Color.white, LogoColor.BLACK );

	private static ColorConfig Black_White_green = new ColorConfig( "0_1_3",
		new Color( 0,0,0,200), Color.white, new FHWS_Color().green, LogoColor.BLACK );

	private static ColorConfig Black_Orange_white = new ColorConfig( "0_4_1",
		new Color( 0,0,0,200), new FHWS_Color().orange, Color.white, LogoColor.ORANGE );

	private static ColorConfig Black_White_orange = new ColorConfig( "0_1_4",
		new Color( 0,0,0,200), Color.white, new FHWS_Color().orange, LogoColor.ORANGE);

	private static ColorConfig DarkGrey_Orange_black = new ColorConfig( "2_4_0",
		new Color( 86, 86, 86,220), new FHWS_Color().orange, Color.BLACK, LogoColor.ORANGE );

	private static ColorConfig DarkGrey_Orange_white = new ColorConfig( "2_4_1",
		new Color( 86, 86, 86,220), new FHWS_Color().orange, Color.white, LogoColor.ORANGE );

	private static ColorConfig DarkGrey_Green_black = new ColorConfig( "2_3_0",
		new Color( 86, 86, 86,220), new FHWS_Color().green, Color.BLACK, LogoColor.BLACK );

	private static ColorConfig DarkGrey_Green_white = new ColorConfig( "2_3_1",
		new Color( 86, 86, 86,220), new FHWS_Color().green, Color.white, LogoColor.WHITE );

	private static ColorConfig Orange_White_White = new ColorConfig( "4_1_1",
		new Color( 237, 110, 0,220), Color.white, Color.white, LogoColor.ORANGE );

	private static ColorConfig Orange_black_grey = new ColorConfig( "4_0_0",
		new Color( 237, 110, 0,220), Color.BLACK, Color.black, LogoColor.ORANGE );

	private static ColorConfig Orange_black_white = new ColorConfig( "4_0_1",
		new Color( 237, 110, 0,220), Color.BLACK, Color.white, LogoColor.ORANGE );

	private static ColorConfig White_Orange_grey = new ColorConfig( "1_4_2",
		new Color( 255, 255, 255,230),new FHWS_Color().orange, new FHWS_Color().greyDark, LogoColor.ORANGE );

	private static ColorConfig White_Green_grey = new ColorConfig( "1_3_2",
		new Color( 255, 255, 255,230),new FHWS_Color().green, new FHWS_Color().greyDark, LogoColor.WHITE );

	private static ColorConfig White_Black_black = new ColorConfig( "1_0_0",
		new Color( 255, 255, 255,230), Color.BLACK, Color.black, LogoColor.BLACK );


	public static List<ColorConfig> allColorConfigurationsPhoto = Arrays.asList( Green_Black, Black_Green_white,
		Orange_black_grey, Orange_black_white, Green_White_White, DarkGrey_Orange_black, Orange_White_White,
		Black_Orange_white, DarkGrey_Orange_white, Green_Black_Grey, White_Orange_grey, White_Black_black,
		White_Green_grey, DarkGrey_Green_black, DarkGrey_Green_white, Black_White_green, Black_White_orange);
}
