package de.fhws.fiw.instagram.wayThroughStudies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static ImportData importData = new ImportData(  );
	static List<String> importList = new ArrayList<>(  );

	public static ImportData getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return importData;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		importData = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
