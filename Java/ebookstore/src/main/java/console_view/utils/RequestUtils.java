package console_view.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ebookstore.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public RequestUtils() {
    }

    public int newId(List<Request> requests) {
        int id = 0;
        List<Integer> maxId = new ArrayList<>();
        for (Request request : requests) {
            maxId.add(request.getIdRequest());
        }
        id = Collections.max(maxId) + 1;
        return id;
    }

    public int inputIdRequest(List<Request> requests) throws IOException {
        int id = 0;
        while (id == 0) {
            LOGGER.info("Введите ID запроса (целое число)/Enter the request ID (integer): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in)) {
                id = Integer.parseInt(in);
                for (Request request : requests) {
                    if (request.getIdRequest() == id) {
                        return id;
                    }
                }
            } else LOGGER.info("Введенное значение не является целым числом/The value entered is not an integer.");
        }
        LOGGER.info("Запроса с таким ID отсутствует/There is no request with this ID.");
        return inputIdRequest(requests);
    }

    public LocalDate inputDate() throws IOException {
        LocalDate date = LocalDate.of(2000, 1, 1);
        LOGGER.info("Введите дату поступления запроса (цифрами в формате dd-MM-yyyy)/Enter the date of the request (in the format dd-MM-yyyy): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        try {
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date = LocalDate.parse(str, dt);

        } catch (Exception e) {
            LOGGER.error("Неправильный формат даты/The date format is incorrect.");
            inputDate();
        }
        return date;
    }

    public String inputStatus() throws IOException {
        String status = null;
        while (status == null) {
            LOGGER.info("Укажите статус запроса (1 -  новый/2 - выполнен/3 - отменен)/Specify the status of the request (1-new/2-completed/3-canceled): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in) && in.equals("1")) {
                status = "New";
                return status;
            } else if (MenuUtils.isInteger(in) && in.equals("2")) {
                status = "Completed";
                return status;
            } else if (MenuUtils.isInteger(in) && in.equals("3")) {
                status = "Canceled";
                return status;
            } else LOGGER.error("ОШИБКА! Введите 1/2/3/Error! Enter 1/2/3.");
        }
        return status;
    }
}
