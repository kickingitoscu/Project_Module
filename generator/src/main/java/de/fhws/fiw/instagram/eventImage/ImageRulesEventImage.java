package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.ImageRules;

public class ImageRulesEventImage
{
	public static int frameWidth = 900;
	public static int frameHeight = 700;
	public static int fillFrameWidth = 880;
	public static int fillFrameHeight = 680;
	public static int freeSpaceYToTextStart = 200;
	public static int freeSpaceXToTextStart =  150;
	public static int freeSpaceForText = ImageRules.pictureWidth -2*freeSpaceXToTextStart;
	public static int maxLinesOfHeader = 2;
	public static int maxLinesOfSubheader = 2;
	public static int maxLinesOfContent = 1;


	public static int logoXCoordinate = 800;
	public static int logoYCoordinate = 980;
}
