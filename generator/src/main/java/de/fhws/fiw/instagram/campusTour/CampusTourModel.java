package de.fhws.fiw.instagram.campusTour;

import de.fhws.fiw.instagram.utils.AbstractInstagramModel;

import java.awt.*;

public class CampusTourModel extends AbstractInstagramModel
{
	private String titleFirstPage;
	private String titleSecondPage;
	private String titleThirdPage;
	private Image photoPathFirstPage;
	private Image photoPathSecondPage;
	private Image photoPathThirdPage;
	private String outputPath;


	public CampusTourModel(){}

	public String getTitleFirstPage( )
	{
		return titleFirstPage;
	}

	public void setTitleFirstPage( String titleFirstPage )
	{
		this.titleFirstPage = titleFirstPage;
	}

	public String getTitleSecondPage( )
	{
		return titleSecondPage;
	}

	public void setTitleSecondPage( String titleSecondPage )
	{
		this.titleSecondPage = titleSecondPage;
	}

	public String getTitleThirdPage( )
	{
		return titleThirdPage;
	}

	public void setTitleThirdPage( String titleThirdPage )
	{
		this.titleThirdPage = titleThirdPage;
	}

	public Image getPhotoFirstPage( )
	{
		return photoPathFirstPage;
	}

	public void setPhotoFirstPage( Image photoPathFirstPage )
	{
		this.photoPathFirstPage = photoPathFirstPage;
	}

	public Image getPhotoSecondPage( )
	{
		return photoPathSecondPage;
	}

	public void setPhotoSecondPage( Image photoPathSecondPage )
	{
		this.photoPathSecondPage = photoPathSecondPage;
	}

	public Image getPhotoThirdPage( )
	{
		return photoPathThirdPage;
	}

	public void setPhotoThirdPage( Image photoPathThirdPage )
	{
		this.photoPathThirdPage = photoPathThirdPage;
	}

	public String getOutputPath( )
	{
		return outputPath;
	}

	public void setOutputPath( String outputPath )
	{
		this.outputPath = outputPath;
	}
}
