package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.LogoColor;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ColorData
{
	private static final ColorConfig config_grey_green = new ColorConfig("grey_green_light", new FHWS_Color().greyLight,
		new FHWS_Color().grey, Color.white, new FHWS_Color().green, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, new FHWS_Color().greyDark, Color.white, LogoColor.BLACK );

	private static final ColorConfig config_grey_green_2 = new ColorConfig("grey_green", new FHWS_Color().greyLight,
		new FHWS_Color().green, Color.white, new FHWS_Color().green, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, new FHWS_Color().greyDark, Color.white, LogoColor.BLACK );

	private static final ColorConfig config_grey_orange_light = new ColorConfig("grey_orange_light", new FHWS_Color().greyLight,
		new FHWS_Color().grey, Color.white, new FHWS_Color().greyDark, new FHWS_Color().grey, new FHWS_Color().orange,
		new FHWS_Color().greyMiddelDark, new FHWS_Color().orange, Color.white, LogoColor.ORANGE );

	private static final ColorConfig config_grey_orange = new ColorConfig("grey_orange", new FHWS_Color().greyLight,
		new FHWS_Color().orange, Color.white, new FHWS_Color().orange, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, new FHWS_Color().greyDark, Color.white, LogoColor.ORANGE );

	private static final ColorConfig config_orange = new ColorConfig("orange", new FHWS_Color().orange,
		new FHWS_Color().grey, Color.white, new FHWS_Color().greyDark, new FHWS_Color().grey, new FHWS_Color().orange,
		new FHWS_Color().greyMiddelDark,  Color.white, Color.white, LogoColor.BLACK );

	private static final ColorConfig config_black_green = new ColorConfig("black_green", Color.black,
		new FHWS_Color().green, Color.white, new FHWS_Color().green, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, Color.white, new FHWS_Color().green, LogoColor.WHITE );

	private static final ColorConfig config_black_orange = new ColorConfig("black_orange", Color.black,
		new FHWS_Color().orange, Color.white, new FHWS_Color().orange, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, Color.white, new FHWS_Color().orange, LogoColor.ORANGE );

	private static final ColorConfig config_greyDark_green = new ColorConfig("greyDark_green", new FHWS_Color().greyDark,
		new FHWS_Color().green, Color.white, new FHWS_Color().green, new FHWS_Color().grey, new FHWS_Color().greyDark,
		new FHWS_Color().greyMiddelDark, Color.white, new FHWS_Color().green, LogoColor.WHITE );




	public static List<ColorConfig> allColorConfiguration = Arrays.asList( config_grey_green, config_grey_green_2,
		config_grey_orange, config_grey_orange_light, config_orange, config_black_green , config_black_orange, config_greyDark_green);


}
