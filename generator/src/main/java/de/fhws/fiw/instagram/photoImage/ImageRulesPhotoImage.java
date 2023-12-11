package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.ImageRules;

public class ImageRulesPhotoImage
{
	public static int freeSpaceToFrameStart = 20;
	public static int freeSpaceToHeadlineStartFromFrame = 30;
	public static int freeSpaceForText = ImageRules.pictureWidth -getFreeSpaceToFrameStartBothSides()-getFreeSpaceToHeadlineStartBothSides();
	public static int maxLinesOfHeader = 3;
	public static int maxLinesOfSubheader = 2;
	public static int headerMaxTextSize = 70;
	public static int headerMinTextSize = 50;
	public static int subheaderMaxTextSize = 45;
	public static int subheaderMinTextSize = 30;
	public static int freeSpaceToTextStart =  getFreeSpaceToTextStart();

	private static int getFreeSpaceToTextStart(){
		return freeSpaceToFrameStart + freeSpaceToHeadlineStartFromFrame;
	}

	private static int getFreeSpaceToFrameStartBothSides(){
		return 2*freeSpaceToFrameStart;
	}

	private static int getFreeSpaceToHeadlineStartBothSides(){
		return 2* freeSpaceToHeadlineStartFromFrame;
	}

}
