package de.fhws.fiw.instagram.photoImage;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

public class AdaptTextPhotoImage
{
	private ComponentPropertiesConfig componentPropertiesConfig;
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptTextPhotoImage(ComponentPropertiesConfig componentPropertiesConfig){
		initializeOutputObject();
		initializeInputObject();
		this.componentPropertiesConfig = componentPropertiesConfig;
	}

	private void initializeInputObject( )
	{
		textSpecificationInput = new TextSpecificationInput(  );
		setFreeSpaceForTextInInputObject();
	}

	private void setFreeSpaceForTextInInputObject()
	{
		textSpecificationInput.setFreeSpaceForText( ImageRulesPhotoImage.freeSpaceForText );
	}


	private void initializeOutputObject(){
		textSpecificationOutput = new TextSpecificationOutput();
	}

	public TextSpecificationOutput adaptHeaderConfig(String header){
		generateInputObjectHeaderForTextDetermination(header);
		identifyTextsize();
		return textSpecificationOutput;
	}


	public TextSpecificationOutput adaptSubheaderConfig(String subheader){
		generateInputObjectSubheaderForTextDetermination( subheader);
		identifyTextsize();
		return textSpecificationOutput;
	}

	private void generateInputObjectHeaderForTextDetermination( String header )
	{
		setInputText( header );
		setFontInInputObject();
		setFontStyleInInputObjectHeader();
		setMinMaxSizesHeader();
		setMaxLinesOfTextHeader();
	}

	private void generateInputObjectSubheaderForTextDetermination( String subheader )
	{
		setInputText( subheader );
		setFontInInputObject();
		setFontSytleInInputObjectSubheader();
		setMinMaxSizesSubheader();
		setMaxLinesOfTextSubheader();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void identifyTextsize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution(  );
	}

	private void setMaxLinesOfTextHeader(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPhotoImage.maxLinesOfHeader );
	}

	private void setMaxLinesOfTextSubheader(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesPhotoImage.maxLinesOfSubheader);
	}


	private void setMinMaxSizesHeader(){
		setMaxSize( ImageRulesPhotoImage.headerMaxTextSize );
		setMinSize( ImageRulesPhotoImage.headerMinTextSize );
	}

	private void setMinMaxSizesSubheader(){
		setMaxSize( ImageRulesPhotoImage.subheaderMaxTextSize );
		setMinSize( ImageRulesPhotoImage.subheaderMinTextSize );
	}

	private void setMinSize( int minSize )
	{
		textSpecificationInput.setMinTextSize( minSize );
	}

	private void setMaxSize( int maxSize )
	{
		textSpecificationInput.setMaxTextSize( maxSize );
	}

	private void setFontInInputObject(){
		textSpecificationInput.setFont( componentPropertiesConfig.getFont( ) );
	}

	private void setFontStyleInInputObjectHeader(){
		textSpecificationInput.setFontStyle( componentPropertiesConfig.getHeaderFontStyle() );
	}

	private void setFontSytleInInputObjectSubheader(){
		textSpecificationInput.setFontStyle( componentPropertiesConfig.getSubheaderFontStyle( ) );
	}


}
