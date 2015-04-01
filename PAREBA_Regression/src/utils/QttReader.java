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

	public void ingQttReader(Logger lg, String account, String bicCode,
			ArrayList<String> qttPareba) throws IOException {

		if (BICCODE.bicCodes.containsValue(bicCode)) {
			if (Integer.parseInt(account.substring(3, 4)) == 5
					|| Integer.parseInt(account.substring(3, 4)) == 6) {
				System.out.println("TEST!");
				file = new File(".\\settings\\GroenBoekje.txt");
				qttValidation(lg, bicCode, qttPareba, file);
			} else {
				file = new File(".\\settings\\qttcodes_ing.txt");
				qttValidation(lg, bicCode, qttPareba, file);
			}
		} else {
			file = new File(".\\settings\\qttcodes_noning.txt");
			qttValidation(lg, bicCode, qttPareba, file);
		}
	}

	private void qttValidation(Logger lg, String bicCode,
			ArrayList<String> qttPareba, File file) throws IOException {

		try {
			inStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inStream));
			line = reader.readLine();
			while (!line.startsWith(bicCode.substring(4, 6))) {
				line = reader.readLine();
			}

			String qtts[] = line.substring(3).split(",");
			List<String> qttList = new ArrayList<String>(Arrays.asList(qtts));
			Collections.sort(qttList);

			if (qttList.equals(qttPareba)) {
				lg.writeResult("\tAll QTT's are Correct for this Account!!");
				System.out.println("All QTT's are Correct for this Account!!");
			} else if (qttList.size() == 1) {
				lg.writeResult("\tMissing QTT LIST for this country!!");
				System.out.println("QTT LIST missing for this country!!");
			} else if (qttPareba.size() < qttList.size()) {
				for (int i = 1; i < qttList.size(); i++) {
					if (!qttPareba.contains(qttList.get(i))) {
						lg.writeResult("\tMissing: " + qttList.get(i).toString()
								+ " in PAREBA");
						System.out.println("Missing: "
								+ qttList.get(i).toString() + " in PAREBA");
					}
				}
			} else if (qttList.size() < qttPareba.size()) {
				for (int i = 1; i < qttPareba.size(); i++) {
					if (!qttList.contains(qttPareba.get(i))) {
						lg.writeResult("\tQTT: " + qttPareba.get(i).toString()
								+ " too much in PAREBA");
						System.out.println("\tQTT: "
								+ qttPareba.get(i).toString()
								+ " too much in PAREBA");
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
