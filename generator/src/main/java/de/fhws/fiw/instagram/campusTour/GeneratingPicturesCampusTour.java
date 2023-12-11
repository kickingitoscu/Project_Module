package de.fhws.fiw.instagram.campusTour;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPicturesCampusTour
{
	private CampusTourModel campusTourModel;
	private TransformatorInputData transformatorInputData;

	public void generatePictures( CampusTourModel campusTourModel ) throws IOException
	{
		this.campusTourModel = campusTourModel;
		transformInputDataForEachPage();
		generateEventImages( );
	}

	private void generateEventImages( ) throws IOException
	{
		generateFirstPage();
		generateSecondPage();
		generateThirdPage();
	}

	private void transformInputDataForEachPage(){
		transformatorInputData = new TransformatorInputData( campusTourModel );
	}

	private void generateFirstPage() throws IOException
	{
		String outputPath = campusTourModel.getOutputPath()+"CampusTour1.jpg";
		BufferedImage campusTour = new GeneratingPictureCampusTour().generateImage(
			transformatorInputData.getImportDataPage1( ) );
		ImageIO.write(campusTour, "jpeg", new File(outputPath));
	}

	private void generateSecondPage() throws IOException
	{
		String outputPath = campusTourModel.getOutputPath()+"CampusTour2.jpg";
		BufferedImage campusTour = new GeneratingPictureCampusTour().generateImage(
			transformatorInputData.getImportDataPage2( ) );
		ImageIO.write(campusTour, "jpeg", new File(outputPath));
	}

	private void generateThirdPage() throws IOException
	{
		String outputPath = campusTourModel.getOutputPath()+"CampusTour3.jpg";
		BufferedImage campusTour = new GeneratingPictureCampusTour().generateImage(
			transformatorInputData.getImportDataPage3() );
		ImageIO.write(campusTour, "jpeg", new File(outputPath));
	}
}
