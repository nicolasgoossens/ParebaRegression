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

	private File logFile;

	public void createLogging() throws IOException {
		FileWriter fw = null;
		PrintWriter pw = null;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());

		try {

			logFile = new File(".\\Logging\\logfile_" + timeStamp + ".txt");
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
			out.println("\nSubscription: "
					+ pareba.getSubscriptionNumber(pareba.getElement()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
