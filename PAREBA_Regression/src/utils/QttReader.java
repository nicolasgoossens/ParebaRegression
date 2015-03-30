package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import objects.INGQTT;

public class QttReader {

	
	public void ingQttReader(String bicCode) throws IOException {
		InputStream inStream = null;
		BufferedReader reader = null;
		File file = new File(".\\settings\\qttcodes_ing.txt");
		
		try {
			inStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inStream));
			String line = reader.readLine();
			System.out.println("Test " + INGQTT.bicCodes.containsValue(bicCode));
		}
		
		catch (IOException ie) {
			ie.printStackTrace();
		}
		finally {
			inStream.close();
			reader.close();
		}
		
	}
}
