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

	private InputStream inStream = null;
	private BufferedReader reader = null;
	private File file = null;
	private String line;

	public void ingQttReader(String bicCode, ArrayList<String> qttPareba)
			throws IOException {

		if (BICCODE.bicCodes.containsValue(bicCode)) {
			file = new File(".\\settings\\qttcodes_ing.txt");
			qttValidation(bicCode, qttPareba, file);
			System.out.println("ING!!!");

		} else {
			file = new File(".\\settings\\qttcodes_noning.txt");
			qttValidation(bicCode, qttPareba, file);
			System.out.println("NONING!!!");
		}
	}

	private void qttValidation(String bicCode, ArrayList<String> qttPareba,
			File file) throws IOException {

		try {
			inStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inStream));
			line = reader.readLine();
			while (!line.startsWith(bicCode.substring(4, 6))) {
				line = reader.readLine();
			}

			String qtts[] = line.substring(3).split(",");
			List<String> qttList = new ArrayList<String>(Arrays.asList(qtts));
			System.out.println(qttList.toString());
			System.out.println(qttList.size());
			Collections.sort(qttList);
			
			if (qttList.equals(qttPareba)) {
				System.out.println("Gelukt!!!!!");
			} else if (qttList.size() == 1) {
				System.out.println("Missing QTT LIST");
		//CODE BELOW NOT WORKING!
			} else if (qttPareba.size() < qttList.size()){
				for(int i = 1; i == qttList.size(); i ++) {
					if (qttPareba.contains(qttList.get(i))) {
						System.out.println("Check!!");
					} else {
						System.out.println("Missing: " + qttList.get(i).toString() + " in PAREBA");
					}
				}
			} else if (qttList.size() < qttPareba.size()) {
				for(int i = 1; i == qttPareba.size(); i ++) {
					if (qttList.contains(qttPareba.get(i))) {
						System.out.println("Check!!");
					} else {
						System.out.println("QTT: " + qttPareba.get(i).toString() + " too much in PAREBA");
					}
				}
			}
			
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			inStream.close();
			reader.close();
		}
	}
}
