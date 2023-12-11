package de.fhws.fiw.instagram.information;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;

import java.awt.*;

public class ImageRulesInformation
{
	public static Color textColor = FHWS_Color.orange;
	public static Color frameColor = new Color( 255, 255, 255,230);
	public static int maxLinesOfText = 3;
	public static String font = "DIN";
	public static int minTextSizeText = 50;
	public static int maxTextSizeText = 80;
	public static int iconWidth = 250;
	public static int iconHeight = 250;
	public static int xCoordinateStartTextFrame = 255;
	public static int yCoordinateStartTextFrame = 350;
	public static int frameWidth = ImageRules.pictureWidth - 2*xCoordinateStartTextFrame;
	public static int frame = 165;
	public static int freeSpaceForText = 550;
	public static String iconPath = "src/main/resources/information/iSign.png";
	public static String backgroundPath = "src/main/resources/information/Information.png";
}
