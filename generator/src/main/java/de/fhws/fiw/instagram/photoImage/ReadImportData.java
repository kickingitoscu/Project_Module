package de.fhws.fiw.instagram.photoImage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static PhotoImageModel photoImageModel = new PhotoImageModel(  );
	static List<String> importList = new ArrayList<>(  );

	public static PhotoImageModel getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return photoImageModel;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		photoImageModel = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
