package de.fhws.fiw.instagram.tellMe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPicturesTellMe
{
	private TellMeModel tellMeModel;

	public void generatePictures( TellMeModel data) throws IOException
	{
		this.tellMeModel = data;
		generateEventImages( );
	}

	private void generateEventImages( ) throws IOException
	{
		generateFrontPage();
		generateQuotePage();
		generateDetailPage();
	}

	private void generateFrontPage() throws IOException
	{
		String outputPath = tellMeModel.getOutputPath()+"TellMe1.jpg";
		BufferedImage tellMeImage = new GeneratingPictureFrontPage().generateImage( tellMeModel );
		ImageIO.write(tellMeImage, "jpeg", new File(outputPath));
	}

	private void generateQuotePage() throws IOException
	{
		String outputPath = tellMeModel.getOutputPath()+"TellMe2.jpg";
		BufferedImage tellMeImage = new GeneratingPictureQuotePage().generateImage( tellMeModel );
		ImageIO.write(tellMeImage, "jpeg", new File(outputPath));
	}

	private void generateDetailPage() throws IOException
	{
		String outputPath = tellMeModel.getOutputPath()+"TellMe3.jpg";
		BufferedImage tellMeImage = new GeneratingPictureDetailPage().generateImage( tellMeModel );
		ImageIO.write(tellMeImage, "jpeg", new File(outputPath));
	}
}
