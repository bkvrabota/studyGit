package console_view.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ebookstore.model.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public OrderUtils() {
    }

    public int newId(List<Order> orders) {
        int id = 0;
        List<Integer> maxId = new ArrayList<>();
        for (Order order : orders) {
            maxId.add(order.getIdOrder());
        }
        id = Collections.max(maxId) + 1;
        return id;
    }

    public int inputIdOrder(List<Order> orders) throws IOException {
        int id = 0;
        while (id == 0) {
            LOGGER.info("Введите ID заказа (целое число)/Enter the order ID (integer): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in)) {
                id = Integer.parseInt(in);
                for (Order order : orders) {
                    if (order.getIdOrder() == id) {
                        return id;
                    }
                }
            } else LOGGER.info("Введенное значение не является целым числом/The value entered is not an integer.");
        }
        LOGGER.info("Заказ с таким ID отсутствует/There is no order with this ID.");
        return inputIdOrder(orders);
    }

    public String inputStatus() throws IOException {
        String status = null;
        while (status == null) {
            LOGGER.info("Укажите статус заказа (1 -  новый/2 - выполнен/3 - отменен)/Specify the order status (1-new/2-completed/3-canceled): ");
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

    public LocalDate inputDate() throws IOException {
        LocalDate date = LocalDate.of(2000, 1, 1);
        LOGGER.info("Введите дату (цифрами в формате dd-MM-yyyy)/Enter the date (in the format dd-MM-yyyy): ");
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

    public String inputSort() throws IOException {
        String sort = null;
        while (sort == null) {
            LOGGER.info("Укажите вид сортировки (1 - цена/2 - дата/3 - статус)/Specify the sort type (1-price/2-date/3-status): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine().toLowerCase();
            if (MenuUtils.isInteger(in)) {
                int num = Integer.parseInt(in);
                switch (num) {
                    case 1:
                        sort = "price";
                        break;
                    case 2:
                        sort = "date";
                        break;
                    case 3:
                        sort = "status";
                        break;
                    default:
                        LOGGER.error("ОШИБКА! Введите один из предложенных вариантов (число)/Error! Enter one of the proposed options (number).");
                        break;
                }
                return sort;
            } else LOGGER.error("ОШИБКА! Введите один из предложенных вариантов (число)/Error! Enter one of the proposed options (number).");
        }
        return sort;
    }
}
