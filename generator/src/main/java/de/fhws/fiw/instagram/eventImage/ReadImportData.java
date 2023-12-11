package de.fhws.fiw.instagram.eventImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static EventImageModel eventImageModel = new EventImageModel(  );
	static List<String> importList = new ArrayList<>(  );

	public static EventImageModel getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return eventImageModel;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		eventImageModel = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
