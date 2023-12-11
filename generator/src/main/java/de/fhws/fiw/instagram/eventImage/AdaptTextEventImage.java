package de.fhws.fiw.instagram.eventImage;

import de.fhws.fiw.instagram.utils.TextDistribution;
import de.fhws.fiw.instagram.utils.TextSpecificationInput;
import de.fhws.fiw.instagram.utils.TextSpecificationOutput;

public class AdaptTextEventImage
{
	private ComponentPropertiesConfig componentPropertiesConfig;
	private TextSpecificationInput textSpecificationInput;
	private TextSpecificationOutput textSpecificationOutput;


	public AdaptTextEventImage(ComponentPropertiesConfig componentPropertiesConfig){
		this.componentPropertiesConfig = componentPropertiesConfig;
		initializeOutputObject();
		initializeInputObject();
	}

	private void initializeInputObject( )
	{
		textSpecificationInput = new TextSpecificationInput(  );
		setFreeSpaceForTextInInputObject();
	}

	private void setFreeSpaceForTextInInputObject(){
		textSpecificationInput.setFreeSpaceForText( ImageRulesEventImage.freeSpaceForText );
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

	public TextSpecificationOutput adaptContentConfig( String content){
		generateInputObjectContentForTextDetermination( content );
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
		setFontStyleInInputObjectSubheader();
		setMinMaxSizesSubheader();
		setMaxLinesOfTextSubheader();
	}

	private void generateInputObjectContentForTextDetermination( String content )
	{
		setInputText( content );
		setFontInInputObject();
		setFontStyleInInputObjectContent();
		setMinMaxSizesContent();
		setMaxLinesOfTextContent();
	}

	private void setInputText(String inputText){
		textSpecificationInput.setInputText( inputText );
	}

	private void identifyTextsize(){
		textSpecificationOutput = new TextDistribution(textSpecificationInput).determineTextDistribution( );
	}

	private void setMaxLinesOfTextHeader(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesEventImage.maxLinesOfHeader );
	}

	private void setMaxLinesOfTextSubheader(){
		textSpecificationInput.setMaxLinesOfText( ImageRulesEventImage.maxLinesOfSubheader);
	}

	private void setMaxLinesOfTextContent(){
		textSpecificationInput.setMaxLinesOfText( ( ImageRulesEventImage.maxLinesOfContent ) );
	}

	private void setMinMaxSizesHeader(){
		setMaxSize( componentPropertiesConfig.getHeaderMaxSize() );
		setMinSize( componentPropertiesConfig.getHeaderMinSize() );
	}

	private void setMinMaxSizesSubheader(){
		setMaxSize( componentPropertiesConfig.getSubheaderMaxSize( ) );
		setMinSize( componentPropertiesConfig.getSubheaderMinSize() );
	}

	private void setMinMaxSizesContent(){
		setMaxSize( componentPropertiesConfig.getContentMaxSize() );
		setMinSize( componentPropertiesConfig.getContentMinSize() );
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

	private void setFontStyleInInputObjectSubheader(){
		textSpecificationInput.setFontStyle( componentPropertiesConfig.getSubheaderFontStyle( ) );
	}

	private void setFontStyleInInputObjectContent(){
		textSpecificationInput.setFontStyle( componentPropertiesConfig.getContentFontStyle( ) );
	}
}
