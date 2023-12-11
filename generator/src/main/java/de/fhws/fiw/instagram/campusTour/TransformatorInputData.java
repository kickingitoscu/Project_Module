package de.fhws.fiw.instagram.campusTour;

public class TransformatorInputData
{
	private CampusTourModel campusTourModel;
	private ImportDataOnePage importDataPage1, importDataPage2, importDataPage3;

	public TransformatorInputData( CampusTourModel campusTourModel ){
		this.campusTourModel = campusTourModel;
		initialAttributes();
		setImportDataPage1();
		setImportDataPage2();
		setImportDataPage3();
	}

	public ImportDataOnePage getImportDataPage1( )
	{
		return importDataPage1;
	}

	public ImportDataOnePage getImportDataPage2( )
	{
		return importDataPage2;
	}

	public ImportDataOnePage getImportDataPage3( )
	{
		return importDataPage3;
	}

	private void initialAttributes(){
		importDataPage1 = new ImportDataOnePage();
		importDataPage2 = new ImportDataOnePage();
		importDataPage3 = new ImportDataOnePage();
	}

	private void setImportDataPage1(){
		importDataPage1.setTitle( campusTourModel.getTitleFirstPage());
		importDataPage1.setPhoto( campusTourModel.getPhotoFirstPage() );
	}

	private void setImportDataPage2(){
		importDataPage2.setTitle( campusTourModel.getTitleSecondPage() );
		importDataPage2.setPhoto( campusTourModel.getPhotoSecondPage() );
	}

	private void setImportDataPage3(){
		importDataPage3.setTitle( campusTourModel.getTitleThirdPage() );
		importDataPage3.setPhoto( campusTourModel.getPhotoThirdPage() );
	}
}
