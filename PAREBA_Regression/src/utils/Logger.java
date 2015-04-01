package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import objects.Pareba;

public class Logger {

	public Logger() {		
		
	}
	
	private String date = new SimpleDateFormat("yyyyMMdd")
	.format(Calendar.getInstance().getTime());
	
	private File logFolder = new File(".\\logging\\" + date);
	
	private File logFile;

	public void createLogging() throws IOException {
		FileWriter fw = null;
		PrintWriter pw = null;
		String timeStamp = new SimpleDateFormat("HHmmss")
				.format(Calendar.getInstance().getTime());
		
		try {
			
			if(!logFolder.exists())
			{
				new File(".\\logging\\" + date).mkdir();
			}
			logFile = new File(".\\Logging\\" + date + "\\logfile_" + timeStamp + ".txt");
			fw = new FileWriter(logFile);
			pw = new PrintWriter(fw);

		} catch (IOException io) {
			io.printStackTrace();
		}

		finally {
			fw.close();
			pw.close();
		}
	}

	public void writeLog(Pareba pareba, int length, String qttValidation)
			throws IOException {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(logFile, true)))) {
			out.println(pareba
					.getAccount()
					.pseudo(pareba.getAccount().accountNumbers(
							pareba.getElement(), length)).substring(0, 12)
					+ " " + qttValidation);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeSubscriptionAndTimestamp(Pareba pareba) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(logFile, true)))) {
			out.println("\n\t\t\t\t\t\t\t----------> Subscription: "
					+ pareba.getSubscriptionNumber(pareba.getElement())+ " <----------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeResult(String result) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(logFile, true)))) {
			out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
