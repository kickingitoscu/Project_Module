package de.fhws.fiw.instagram.reminder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadImportData
{
	static ReminderModel reminderModel = new ReminderModel(  );
	static List<String> importList = new ArrayList<>(  );

	public static ReminderModel getLoadedUserImportData(String path) throws IOException
	{
		loadUserImportFile(path);
		transferInputInInputObject();

		return reminderModel;
	}

	private static void loadUserImportFile(String path) throws IOException
	{
		importList = Files.readAllLines( Paths.get( ( path ) ) );
	}

	private static void transferInputInInputObject( ) throws IOException
	{
		reminderModel = new TransferInputInInputData().transferInputDataInInputObject( importList );
	}
}
