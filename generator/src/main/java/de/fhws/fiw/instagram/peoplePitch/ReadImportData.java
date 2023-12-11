package de.fhws.fiw.instagram.peoplePitch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static PeoplePitchModel peoplePitchModel = new PeoplePitchModel(  );
	static List<String> importList = new ArrayList<>(  );

	public static PeoplePitchModel getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return peoplePitchModel;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		peoplePitchModel = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
