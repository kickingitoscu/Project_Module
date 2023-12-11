package de.fhws.fiw.instagram.peoplePitch;

import de.fhws.fiw.instagram.utils.ImageRules;

public class ImageRulesPeoplePitchFrontPage
{
	public static int xCoordinateStartFrame = 190;
	public static int yCoordinateStartFrame = 80;
	public static int frameHeight = 700;
	public static int frameWidth = 20;
	public static int xCoordinateText = xCoordinateStartFrame;
	public static int yCoordinateHeadline = yCoordinateStartFrame + frameHeight +90;
	public static int yCoordinateNameAndArea = yCoordinateHeadline +100;
	public static int textSizeNameAndArea = 50;
	public static int minTextSizePeoplePitch = 40;
	public static int maxTextSizePeoplePitch = 110;
	public static int maxLinesOfPeoplePitch = 1;
	public static String font = "DIN";
	public static int maxLinesOfNameAndArea = 2;
	public static int freeSpaceXForText = ImageRules.pictureWidth-2* xCoordinateStartFrame;
}
