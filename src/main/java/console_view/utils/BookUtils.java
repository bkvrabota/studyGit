package console_view.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ebookstore.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public BookUtils() {
    }

    public int newId(List<Book> books) {
        int id = 0;
        List<Integer> maxId = new ArrayList<>();
        for (Book book : books) {
            maxId.add(book.getIdBook());
        }
        id = Collections.max(maxId) + 1;
        return id;
    }

    public Set<Book> addBuyBooks(List<Book> books) throws IOException {
        Set<Book> buyBooks = new HashSet<>();
        boolean addBook = true;
        while (addBook) {
            LOGGER.info("Добавить книгу?/Add book? ");
            addBook = MenuUtils.requestConfirmation();
            if (addBook) {
                int id = inputId(books);
                for (Book book : books) {
                    if (book.getIdBook() == id) {
                        buyBooks.add(book);
                    }
                }
            }
        }
        return buyBooks;
    }

    public int inputId(List<Book> books) throws IOException {
        int id = 0;
        while (id == 0) {
            LOGGER.info("Введите ID книги (целое число)/Enter the ID book (integer): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in)) {
                id = Integer.parseInt(in);
                for (Book book : books) {
                    if (book.getIdBook() == id) {
                        return id;
                    }
                }
            } else LOGGER.info("Введенное значение не является целым числом./The value entered is not an integer.");
        }
        LOGGER.info("Книга с таким ID отсутствует./There is no book with such ID.");
        return inputId(books);
    }

    public String inputGenre() throws IOException {
        String genre = null;
        while (genre == null) {
            LOGGER.info("Укажите жанр книги/What's the genre of the book: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (!MenuUtils.isInteger(in)) {
                genre = in;
                return genre;
            } else LOGGER.error("ОШИБКА! Жанр не может быть числом/Error! The genre can't be a number.");
        }
        return genre;
    }

    public String inputPublisher() throws IOException {
        String publisher = null;
        while (publisher == null) {
            LOGGER.info("Укажите издателя книги/Specify the publisher of the book: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (!MenuUtils.isInteger(in)) {
                publisher = in;
                return publisher;
            } else LOGGER.error("ОШИБКА! Издатель не может быть числом/Error! Publisher can't be a number.");
        }
        return publisher;
    }

    public String inputAuthor() throws IOException {
        String author = null;
        while (author == null) {
            LOGGER.info("Укажите автор книги (Фамилия И.О.)/Specify the author of the book (FIO): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (!MenuUtils.isInteger(in)) {
                author = in;
                return author;
            } else LOGGER.error("ОШИБКА! Автор не может быть числом/Error! The author cannot be a number.");
        }
        return author;
    }

    public String inputTitle() throws IOException {
        String title = null;
        while (title == null) {
            LOGGER.info("Укажите название книги/Specify the title of the book: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            title = in;
            return title;
        }
        return title;
    }

    public String inputDescription() throws IOException {
        String description = null;
        while (description == null) {
            LOGGER.info("Укажите описание книги/Specify the description of the book: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (!MenuUtils.isInteger(in)) {
                description = in;
                return description;
            } else LOGGER.error("ОШИБКА! Описание не может быть числом/Error! Description can't be a number.");
        }
        return description;
    }

    public double inputPrice() throws IOException {
        double price = 0;
        while (price == 0) {
            LOGGER.info("Введите цену книги/Enter the price of the book: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isDouble(in)) {
                price = Double.parseDouble(in);
                return price;
            } else LOGGER.info("Введенное значение не является числом/The value entered is not a number.\n");
        }
        return price;
    }

    public LocalDate inputDate() throws IOException {
        LocalDate date = LocalDate.of(2000, 1, 1);
        LOGGER.info("Введите дату поступления (цифрами в формате dd-MM-yyyy)/Enter the date of receipt (in numbers in the format dd-MM-yyyy): ");
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

    public Boolean inputAvailable() throws IOException {
        Boolean available = null;
        while (available == null) {
            LOGGER.info("Укажите статус книги (1 -  в наличии/0 - отсутствует)/Specify the status of the book (1-available/0-not available): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (MenuUtils.isInteger(in) && in.equals("1")) {
                available = true;
                return available;
            } else if (MenuUtils.isInteger(in) && in.equals("0")) {
                available = false;
                return available;
            } else LOGGER.error("ОШИБКА! Введите 1 или 0/Error! Enter 1 or 0.");
        }
        return available;
    }

    public String inputSort() throws IOException {
        String sort = null;
        while (sort == null) {
            LOGGER.info("Укажите вид сортировки (1 - автор/2 - дата/3 - статус)/Specify the sort type (1-author/2-date/3-status): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine().toLowerCase();
            if (MenuUtils.isInteger(in)) {
                int num = Integer.parseInt(in);
                switch (num) {
                    case 1:
                        sort = "author";
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
