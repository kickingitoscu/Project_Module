package de.fhws.fiw.instagram.fiwRecommends;

import de.fhws.fiw.instagram.utils.FHWS_Color;
import de.fhws.fiw.instagram.utils.ImageRules;

import java.awt.*;

public class ImageRulesFIWRecommends
{
	public static Color textColor = FHWS_Color.orange;
	public static Color frameColor = new Color( 255, 255, 255,230);
	public static int maxLinesOfText = 2;

	public static String font = "DIN";
	public static int minTextSizeText = 50;
	public static int maxTextSizeText = 80;
	public static int textSizeFIWRecommends = 70;

	public static int borderFrameWidth = 165;
	public static int freeSpaceForText = ImageRules.pictureWidth - 2*borderFrameWidth - 20;
	public static int borderFrameHeight = borderFrameWidth;
	public static int textFrameWidth = ImageRules.pictureWidth-2* borderFrameWidth;
	public static int xCoordinateStartHeadlineAndTextFrame = borderFrameWidth;
	public static int yCoordinateStartHeadlineFrame = 330;
	public static int textFrameHeightHeadline = 2*textSizeFIWRecommends;
	public static int yCoordinateStartTextFrame = yCoordinateStartHeadlineFrame + textFrameHeightHeadline;
	public static int frameForCorners = 120;
	public static int cornerLinesLength = 220;
	public static int cornerLinesWidth = 15;
	public static int arcValueCornerLines = 10;

	public static int imageHeight = ImageRules.pictureHeight-2* borderFrameHeight;

	public static String fiwRecommends = "#FIWrecommends";
	public static String yourTips = "Eure Tipps";




}
