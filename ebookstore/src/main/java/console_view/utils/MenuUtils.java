package console_view.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MenuUtils {

	private static final Logger LOGGER = LogManager.getLogger();

	public MenuUtils() {
	}

	public static void pressEnterKey(){
		LOGGER.info("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}

	// Запрос на подтверждение
	public static boolean requestConfirmation() throws IOException {
		while (true) {
			LOGGER.info("(y/n)... ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String in = reader.readLine().toLowerCase();
			if (in.equals("y") || in.equals("yes"))
				return true;
			else if (in.equals("n") || in.equals("no"))
				return false;
		}
	}

	public void confirmOption() throws IOException {
		boolean confirm = MenuUtils.requestConfirmation();
		if (confirm) {
			LOGGER.info("\nПодтверждено.");
		} else {
			LOGGER.info("\nОтклонено!");
		}
		MenuUtils.pressEnterKey();
	}

	// Является ли строка целым числом
	public static boolean isInteger(String s) throws NumberFormatException {
		try {
			Integer.parseInt(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	// Является ли строка числом
	public static boolean isDouble(String s) throws NumberFormatException {
		try {
			Double.parseDouble(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}