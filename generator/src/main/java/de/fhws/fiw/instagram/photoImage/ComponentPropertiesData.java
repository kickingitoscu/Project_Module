package de.fhws.fiw.instagram.photoImage;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ComponentPropertiesData
{
	/*private static int headerMaxSize = 70;
	private static int headerMinSize = 50;
	private static int subheaderMaxSize = 50;
	private static int subheaderMinSize = 30;

	private static  int xCooridnateHeader = 50;
	private static  int xCooridnateSubheader = 50;
	private static int frameXCoordinate = 20;*/


	/*
	Name: Photo
	_
	Position Text:
		0: top
		1: down
	_
	Position Logo:
		0: left
		1: right
	_
	Cut of the imported image:
		0: beginning
		1: middle
		2: end
	 */

	//( String name, String font, int headerFontStyle, int subheaderFontStyle,
	//		LogoPosition logoPosition, CutPositionPicture cutPositionPicture )


	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_l_Schnitt_a = new ComponentPropertiesConfig(
		"Photo_1_0_0", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.TOP_LEFT, CutPositionPicture.BEGINNING );

	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_r_Schnitt_a = new ComponentPropertiesConfig(
		"Photo_1_1_0", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.TOP_RIGHT, CutPositionPicture.BEGINNING );

	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_r_Schnitt_m = new ComponentPropertiesConfig(
		"Photo_1_1_1", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.TOP_RIGHT, CutPositionPicture.CENTER );

	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_l_Schnitt_m = new ComponentPropertiesConfig(
		"Photo_1_0_1", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.TOP_LEFT, CutPositionPicture.CENTER );

	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_l_Schnitt_e = new ComponentPropertiesConfig(
		"Photo_1_0_2", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.TOP_LEFT, CutPositionPicture.END );

	private static ComponentPropertiesConfig config_Photo_frame_b_Logo_r_Schnitt_e = new ComponentPropertiesConfig(
		"Photo_1_1_2", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.TOP_RIGHT, CutPositionPicture.END );



	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_l_Schnitt_a = new ComponentPropertiesConfig(
		"Photo_0_0_0", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.BOTTOM_LEFT, CutPositionPicture.BEGINNING );

	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_r_Schnitt_a = new ComponentPropertiesConfig(
		"Photo_0_1_0", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.BOTTOM_RIGHT, CutPositionPicture.BEGINNING );

	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_r_Schnitt_m = new ComponentPropertiesConfig(
		"Photo_0_1_1", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.BOTTOM_RIGHT, CutPositionPicture.CENTER );

	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_l_Schnitt_m = new ComponentPropertiesConfig(
		"Photo_0_0_1", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.BOTTOM_LEFT, CutPositionPicture.CENTER );

	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_l_Schnitt_e = new ComponentPropertiesConfig(
		"Photo_0_0_2", "INTER", Font.BOLD, Font.PLAIN,LogoPosition.BOTTOM_LEFT, CutPositionPicture.END );

	private static ComponentPropertiesConfig config_Photo_frame_t_Logo_r_Schnitt_e = new ComponentPropertiesConfig(
		"Photo_0_1_2", "INTER", Font.BOLD, Font.PLAIN, LogoPosition.BOTTOM_RIGHT, CutPositionPicture.END );


	public static List<ComponentPropertiesConfig> textProperties = Arrays.asList(
		config_Photo_frame_b_Logo_l_Schnitt_a, config_Photo_frame_b_Logo_r_Schnitt_a, config_Photo_frame_b_Logo_r_Schnitt_m,
		config_Photo_frame_b_Logo_l_Schnitt_m, config_Photo_frame_b_Logo_l_Schnitt_e, config_Photo_frame_b_Logo_r_Schnitt_e,
		config_Photo_frame_t_Logo_l_Schnitt_a, config_Photo_frame_t_Logo_r_Schnitt_a, config_Photo_frame_t_Logo_r_Schnitt_m,
		config_Photo_frame_t_Logo_l_Schnitt_m, config_Photo_frame_t_Logo_l_Schnitt_e, config_Photo_frame_t_Logo_r_Schnitt_e
	);

}
