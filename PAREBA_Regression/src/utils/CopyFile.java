package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class CopyFile {

	private String fileName;
	
	public String getTemporaryFileName(int fileNumber) {
		return ".\\Temporary\\" + "Test_FileWriter_" + fileNumber + "_.xml";
	}
	
	private String setFileName(String line) {
		this.fileName = ".\\output\\" + line.substring(308, 315) + "_" + line.substring(280, 288) + "_" + 
				line.substring(289, 291) + "h" + line.substring(292, 294) + "m" + line.substring(295, 297) + "s.xml";
		return fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	// Method to copy the PAREBA txt file to the new directory
	public void copyParebaTxttoXml(File file) throws IOException, InterruptedException {
		InputStream inStream = null;
		BufferedReader reader = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {

			FileUtils.copyFile(new File(file.getAbsolutePath()), new File(".\\done\\" + file.getName()));
			inStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inStream));
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("<?xml")) {
					line = line.replace("</Payload>", "");
					fw = new FileWriter(setFileName(line));
					pw = new PrintWriter(fw);
					pw.println(line);
				}
				line = reader.readLine();
			}

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			inStream.close();
			fw.close();
			pw.close();
			reader.close();
		}
	}

}
