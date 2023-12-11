package de.fhws.fiw.instagram.peoplePitch;

import de.fhws.fiw.instagram.utils.ImageRules;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GeneratingPicturePeoplePitchDetailPage
{
	private BufferedImage image;
	private Graphics2D g;
	private PeoplePitchModel peoplePitchModel;
	private TextSpecificationOutput textSpecificationOutputFirstTaskInTheMorning, textSpecificationOutputFavoriteTask;

	GeneratingPicturePeoplePitchDetailPage( ) //verhindert, dass ein "leeres" Objekt der Klasse erstellt wird, ohne den Builder aufzurufen
	{
	}

	public BufferedImage generatePhoto ( PeoplePitchModel peoplePitchModel ) throws IOException {
		setImportData( peoplePitchModel );
		configureGraphics( );
		placeBackgroundImage( );
		setFontConfigurations();
		placeName();
		placePosition();
		placeSince();
		placeFavoriteTask();
		placeFirstTaskInTheMorning();

		return image;
	}



	private void configureGraphics( ) {
		image = new BufferedImage( ImageRules.pictureWidth, ImageRules.pictureHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP  );
	}

	private void setImportData( PeoplePitchModel peoplePitchModel ) {
		this.peoplePitchModel = peoplePitchModel;
	}

	private void placeBackgroundImage() throws IOException
	{
		Image backgroundImage =  ImageIO.read( this.getClass( ).getResourceAsStream( "/peoplePitch/Papier_Stecknadel.png" ) ) ;

		g.drawImage( backgroundImage, 0, 0, null);
	}

	private void setFontConfigurations(){
		setFontColor();
		setFont();
	}

	private void placeName(){
		drawNameOnPhoto();
	}

	private void placePosition(){
		drawPositionOnPhoto();
	}

	private void placeSince( ){
		drawSinceOnPhoto();
	}

	private void placeFavoriteTask(){
		setFavoriteTaskTextConfiguration();
		drawFavoriteTaskOnPhoto();
	}

	private void placeFirstTaskInTheMorning(){
		setFirstTaskInTheMorningTextConfiguration();
		drawFirstTaskInTheMorningOnPhoto();
	}

	private void drawNameOnPhoto(){
		g.drawString( "NAME: " + peoplePitchModel.getName(), ImageRulesPeoplePitchDetailPage.xCoordinateText,
			ImageRulesPeoplePitchDetailPage.yCoortinateName );
	}

	private void drawPositionOnPhoto(){
		g.drawString( "POSITION: " + peoplePitchModel.getPosition(), ImageRulesPeoplePitchDetailPage.xCoordinateText,
			ImageRulesPeoplePitchDetailPage.yCoortinatePosition);
	}

	private void drawSinceOnPhoto(){
		g.drawString( "SEIT: " + peoplePitchModel.getSince(), ImageRulesPeoplePitchDetailPage.xCoordinateText,
			ImageRulesPeoplePitchDetailPage.yCoortinateSince);
	}

	private void drawFavoriteTaskOnPhoto(){
		drawDescriptionFavoriteTaskOnPhoto();
		drawUserInputFavoriteTaskOnPhoto();
	}

	private void drawFirstTaskInTheMorningOnPhoto(){
		drawDescriptionFirstTaskInTheMorningOnPhoto();
		drawUserInputFirstTaskInTheMorningOnPhoto();
	}

	private void drawDescriptionFavoriteTaskOnPhoto(){
		g.drawString( "LIEBLINGSAUFGABE: ", ImageRulesPeoplePitchDetailPage.xCoordinateText,
			ImageRulesPeoplePitchDetailPage.yCoordinateFavoriteTask);
	}

	private void drawUserInputFavoriteTaskOnPhoto(){
		int yCooridnate = ImageRulesPeoplePitchDetailPage.yCoordinateFavoriteTask;

		for (int i = 0; i < textSpecificationOutputFavoriteTask.getTextPerLine().size(); i++){
			yCooridnate = yCooridnate + ImageRulesPeoplePitchDetailPage.lineBreak;
			g.drawString( textSpecificationOutputFavoriteTask.getTextPerLine().get( i ),
				ImageRulesPeoplePitchDetailPage.xCoordinateText, yCooridnate );
		}
	}

	private void drawDescriptionFirstTaskInTheMorningOnPhoto(){
		g.drawString( "ERSTE TÄTIGKEIT MORGENS AN DER FHWS:" , ImageRulesPeoplePitchDetailPage.xCoordinateText,
			ImageRulesPeoplePitchDetailPage.yCoordinateFirstTaskInTheMorning);
	}

	private void drawUserInputFirstTaskInTheMorningOnPhoto(){
		int yCooridnate = ImageRulesPeoplePitchDetailPage.yCoordinateFirstTaskInTheMorning;

		for (int i = 0; i < textSpecificationOutputFirstTaskInTheMorning.getTextPerLine().size(); i++){
			yCooridnate = yCooridnate + ImageRulesPeoplePitchDetailPage.lineBreak;
			g.drawString( textSpecificationOutputFirstTaskInTheMorning.getTextPerLine().get( i ),
				ImageRulesPeoplePitchDetailPage.xCoordinateText, yCooridnate );
		}
	}

	private void setFavoriteTaskTextConfiguration(){
		textSpecificationOutputFavoriteTask = new AdaptText().adaptFavoriteTask(
			peoplePitchModel.getFavoriteTask( ) );
	}

	private void setFirstTaskInTheMorningTextConfiguration(){
		textSpecificationOutputFirstTaskInTheMorning = new AdaptText().adaptFirstTaskIntTheMorning(
			peoplePitchModel.getFirstTaskInTheMorning( ) );
	}

	private void setFontColor(){
		g.setColor( Color.black );
	}

	private void setFont(){
		g.setFont( new Font( ImageRulesPeoplePitchDetailPage.font,Font.BOLD, ImageRulesPeoplePitchDetailPage.textSize ) );
	}
}

