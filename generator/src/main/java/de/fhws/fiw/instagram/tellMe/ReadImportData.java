package de.fhws.fiw.instagram.tellMe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static TellMeModel tellMeModel = new TellMeModel(  );
	static List<String> importList = new ArrayList<>(  );

	public static TellMeModel getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return tellMeModel;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		tellMeModel = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
