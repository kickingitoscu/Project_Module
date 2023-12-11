package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

import java.util.List;

public class AdaptContent
{
	private List<String> contentList;
	private ComponentPropertiesConfig componentPropertiesConfig;
	private TextSpecificationOutput finalTextspecificationOutput;


	public TextSpecificationOutput adaptContent(ComponentPropertiesConfig componentPropertiesConfig, List<String> contentList ){
		this.contentList = contentList;
		this.componentPropertiesConfig = componentPropertiesConfig;

		getConfigurationForOutputObject();
		identifyTextsizeForAllLinesOfConten();

		return finalTextspecificationOutput;
	}

	private void identifyTextsizeForAllLinesOfConten(){
		TextSpecificationOutput textSpecificationOutputForOneLine;

		textSpecificationOutputForOneLine = getContextConfigurationForOneLine(0);
		int textsize = textSpecificationOutputForOneLine.getTextSize();

		for (int i = 1; i<contentList.size();i++){
			textSpecificationOutputForOneLine = getContextConfigurationForOneLine(i);
			if( textSizeIsSmalerThanTextSizeBefore( textSpecificationOutputForOneLine, textsize ) ){
				textsize = textSpecificationOutputForOneLine.getTextSize();
			}
		}
		finalTextspecificationOutput.setTextSize( textsize );
	}

	private void getConfigurationForOutputObject(){
		finalTextspecificationOutput = getContextConfigurationForOneLine( 0 );
		setContentListInOutputObject();
	}

	private void setContentListInOutputObject(){
		finalTextspecificationOutput.setTextPerLine( contentList );
	}

	private boolean textSizeIsSmalerThanTextSizeBefore( TextSpecificationOutput textSpecificationOutputForOneLine, int textsize )
	{
		return textsize > textSpecificationOutputForOneLine.getTextSize();
	}

	private TextSpecificationOutput getContextConfigurationForOneLine(int lineOfContext){
		return new AdaptTextEventImage( componentPropertiesConfig ).adaptContentConfig(contentList.get( lineOfContext ));
	}
}
