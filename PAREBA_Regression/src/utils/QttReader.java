package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import objects.BICCODE;

public class QttReader {

	public void ingQttReader(String bicCode, ArrayList<String> qttPareba)
			throws IOException {
		InputStream inStream = null;
		BufferedReader reader = null;
		File file = null;
		String line;

		try {
			if (BICCODE.bicCodes.containsValue(bicCode)) {
				file = new File(".\\settings\\qttcodes_ing.txt");
				inStream = new FileInputStream(file);
				reader = new BufferedReader(new InputStreamReader(inStream));
				line = reader.readLine();
				while (!line.startsWith(bicCode.substring(4, 6))) {
					line = reader.readLine();
				}

				String qtts[] = line.substring(3).split(",");
				List<String> qttList = new ArrayList<String>(
						Arrays.asList(qtts));

				Collections.sort(qttList);
				if (qttList.equals(qttPareba)) {
					System.out.println("Gelukt!!!!!");

				}
			} else {
				file = new File(".\\settings\\qttcodes_noning.txt");
				inStream = new FileInputStream(file);
				reader = new BufferedReader(new InputStreamReader(inStream));
				line = reader.readLine();
				while (!line.startsWith(bicCode.substring(4, 6))) {
					line = reader.readLine();
				}
				if (line != null) {
					String qtts[] = line.substring(3).split(",");
					List<String> qttList = new ArrayList<String>(
							Arrays.asList(qtts));

					Collections.sort(qttList);
					if (qttList.equals(qttPareba)) {
						System.out.println("ING!!!!!");
					}
				}
			}
		}

		catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			inStream.close();
			reader.close();
		}

	}
}
