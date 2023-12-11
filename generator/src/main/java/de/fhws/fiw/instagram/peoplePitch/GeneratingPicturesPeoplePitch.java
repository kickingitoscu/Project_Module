package de.fhws.fiw.instagram.peoplePitch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratingPicturesPeoplePitch
{
	private PeoplePitchModel peoplePitchModel;

	public void generatePictures( PeoplePitchModel data) throws IOException
	{
		this.peoplePitchModel = data;
		generateEventImages( );
	}

	private void generateEventImages( ) throws IOException
	{
		generateFrontPagePeoplePitch();
		generateDetailPagePeoplePitch();
	}

	private void generateFrontPagePeoplePitch() throws IOException
	{
		String outputPath = peoplePitchModel.getOutputPath()+"PeoplePitch1.jpg";
		BufferedImage peoplePitchImage = new GeneratingPicturePeoplePitchFrontPage().generatePhoto( peoplePitchModel );
		ImageIO.write(peoplePitchImage, "jpeg", new File(outputPath));
	}

	private void generateDetailPagePeoplePitch() throws IOException
	{
		String outputPath = peoplePitchModel.getOutputPath()+"PeoplePitch2.jpg";
		BufferedImage peoplePitchImage = new GeneratingPicturePeoplePitchDetailPage().generatePhoto( peoplePitchModel );
		ImageIO.write(peoplePitchImage, "jpeg", new File(outputPath));
	}
}
