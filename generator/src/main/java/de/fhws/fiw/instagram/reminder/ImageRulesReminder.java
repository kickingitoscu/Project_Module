package de.fhws.fiw.instagram.reminder;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;

import java.awt.*;

public class ImageRulesReminder
{
	public static Color textColorReminder = FHWS_Color.orange;
	public static Color textColor = Color.BLACK;
	public static Color frameColor = new Color( 255, 255, 255,230);
	public static int maxLinesOfText = 2;
	public static int maxLinesOfHeaders = 1;
	public static String font = "DIN";
	public static int minTextSizeText = 50;
	public static int maxTextSizeText = 60;
	public static int iconWidth = 80; //354 × 443
	public static int iconHeight = 100;
	public static int xCoordinateStartTextFrame = 120;
	public static int yCoordinateStartTextFrame = 350;
	public static int yCoordinateMiddlePointOfFrame = 500;
	public static int frameWidthText = ImageRules.pictureWidth - 2*xCoordinateStartTextFrame;
	public static int frameHeightText = 300;
	public static int frameWidthIcon = 200;
	public static int frameHeightIcon = 180;
	public static int xCoordinateStartOkayFrame = xCoordinateStartTextFrame;
	public static int yCoordinateStartOkayFrame = yCoordinateStartTextFrame + frameHeightText + 10;
	public static int frameWidthOkay = frameWidthText;
	public static int frameHeightOkay = 100;
	public static int freeSpaceForHeaders = frameWidthText - 60;
	public static int minTextSizeReminder = 60;
	public static int maxTextSizeReminder = 75;
	public static int minTextSizeOkay = 55;
	public static int maxTextSizeOkay = 60;
	public static String reminder = "R E M I N D E R";
	public static String okay = "OKAY!";
	public static String iconPath = "src/main/resources/reminder/Lampe.png";
	public static int arcWidthFrame = 20; //the horizontal diameter of the arc at the four corners
	public static int arcHeightFrame = 20; //the vertical diameter of the arc at the four corners

}
