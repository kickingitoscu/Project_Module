package de.fhws.fiw.instagram.eventImage;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ComponentPropertiesData
{

	private static final ComponentPropertiesConfig config_TextBild = new ComponentPropertiesConfig(
		"EventImage", "DIN", 90,90, 100,100,
		150, 25,
		 Font.PLAIN, 45,70, Font.BOLD,
		30, 45,  Font.PLAIN,
		27,40, Font.PLAIN,
		40,880, Font.PLAIN);

	public static List<ComponentPropertiesConfig> allComponentProperties = Arrays.asList( config_TextBild );
}
