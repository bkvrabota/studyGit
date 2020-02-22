package console_view.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ebookstore.model.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public ClientUtils() {
    }

    public int newId(List<Client> clients) {
        int id = 0;
        List<Integer> maxId = new ArrayList<>();
        for (Client client : clients) {
            maxId.add(client.getIdClient());
        }
        id = Collections.max(maxId) + 1;
        return id;
    }

    public int inputId(List<Client> clients) throws IOException {
        int id = 0;
        while (id == 0) {
            LOGGER.info("Введите ID клиента (целое число)/Enter the client ID (integer): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in)) {
                id = Integer.parseInt(in);
                for (Client client : clients) {
                    if (client.getIdClient() == id) {
                        return id;
                    }
                }
            } else LOGGER.info("Введенное значение не является целым числом/The value entered is not an integer.");
        }
        LOGGER.info("Клиент с таким ID отсутствует/There is no client with this ID.");
        return inputId(clients);
    }

    public int inputCard() throws IOException {
        int id = 0;
        while (id == 0) {
            LOGGER.info("Введите номер карты (целое число)/Enter the card number (integer): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in)) {
                id = Integer.parseInt(in);
                return id;
            } else LOGGER.info("Введенное значение не является целым числом/The value entered is not an integer.\n");
        }
        return id;
    }

    public String inputPhone() throws IOException {
        String str = null;
        while (str == null) {
            LOGGER.info("Введите номер телефона/Enter phone number: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            str = in;
            return str;
        }
        return str;
    }

    public String inputFName() throws IOException {
        String str = null;
        while (str == null) {
            LOGGER.info("Введите имя клиента/Enter the client first name: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            str = in;
            return str;
        }
        return str;
    }

    public String inputLName() throws IOException {
        String str = null;
        while (str == null) {
            LOGGER.info("Введите фамилию клиента/Enter the client last name: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            str = in;
            return str;
        }
        return str;
    }
}
