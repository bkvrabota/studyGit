package ebookstore.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ebookstore.dao.interfaces.IBooksDao;
import ebookstore.dao.interfaces.IRequestsDao;
import ebookstore.model.Book;
import ebookstore.model.Request;
import ebookstore.service.interfaces.IStorageService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StorageService implements IStorageService {

    @Autowired
    private IBooksDao booksDao;
    @Autowired
    private IRequestsDao requestsDao;

    @Autowired
    Environment environment;

    private int minusMonth;
    private boolean reqOn;

    @Autowired
    private Logger LOGGER;

    public StorageService() {
    }

    // Добавление новой книги на склад:
    @Override
    public void addBook(Book book) {
        booksDao.add(book);
    }

    // Удаление книги со склада:
    @Override
    public void deleteBook(int idBook) {
        booksDao.deleteById(Book.class, idBook);
    }

    // Изменение статуса книги:
    @Override
    public void editAvailableBook(int idBook, boolean available) {
        reqOn = Boolean.parseBoolean(this.environment.getProperty("reqOn"));
        Book book = checkBook(idBook);
        book.setAvailable(available);
        if (available && reqOn) {
            editStatusReq(findRequestBook(book), "Completed");
        }
        booksDao.update(book);
    }

    // Просмотр всех книг на складе:
    @Override
    public List<Book> checkBooks() {
        return booksDao.getAll(Book.class);
    }

    // Просмотр конкретной книги на складе:
    @Override
    public Book checkBook(int idBook) {
        try {
            return booksDao.findById(Book.class, idBook);
        } catch (Exception ex) {
            LOGGER.error("Ошибка при запросе книги/Error in the request of a book.");

            return null;
        }
    }

    // Просмотр описания книги:
    @Override
    public String checkDescription(int idBook) {
        try {
            return booksDao.findById(Book.class, idBook).getDescription();
        } catch (Exception ex) {
            LOGGER.error("Ошибка при запросе описания книги/Error in requesting a book description.");
            return null;
        }
    }

    // Добавление нового запроса:
    @Override
    public void createRequestBook(Request request) {
        requestsDao.add(request);
    }

    // Удаление запроса:
    @Override
    public void deleteRequestBook(int idRequest) {
        requestsDao.deleteById(Request.class, idRequest);
    }

    // Список запросов:
    @Override
    public List<Request> checkRequestBooks() {
        return requestsDao.getAll(Request.class);
    }

    @Override
    // Смена статуса запроса:
    public void editStatusReq(int idRequest, String newStatus) {
        try {
            Request request = requestsDao.findById(Request.class, idRequest);
            request.setStatusReq(newStatus);
            requestsDao.update(request);
        } catch (Exception ex) {
            LOGGER.info("Запрос с таким ID отсутствует/There is no request for such ID.\n" + ex);
        }
    }

    // Поиск книги среди запросов:
    @Override
    public Integer findRequestBook(Book book) {
        Integer[] idReq = {null};
        try {
            requestsDao.getAll(Request.class)
                    .forEach(request -> Stream.of(request.getRequestBooks())
                            .filter(book::equals)
                            .forEach(bookFind ->  idReq[0] = request.getIdRequest()));
            return idReq[0];
        } catch (Exception ex) {
            LOGGER.error("Ошибка при поиске книги в запросах/Error in finding a book in requests.");
            return null;
        }
    }

    // Список залежавшихся книг:
    @Override
    public List<Book> oldBooks() {
        minusMonth = Integer.parseInt(this.environment.getProperty("minusMonth"));
        LocalDate nowDate = LocalDate.now();
        LocalDate oldDate = nowDate.minusMonths(minusMonth);
        LOGGER.info("Книги до/Books until " + oldDate + " (" + minusMonth + " мес./month)");
        try {
            return booksDao.getOldBooks(oldDate, nowDate);
        } catch (Exception ex) {
            LOGGER.error("Ошибка при поиске залежавшихся книг/Error in finding stale books.\n" + ex);
            return null;
        }
    }

    // Сортировки:
    @Override
    public List<Book> sortBy(String typeSort) {
        try {
            return booksDao.sortBy(typeSort);
        } catch (Exception ex) {
            LOGGER.error("ОШИБКА! Введите один из предложенных вариантов сортировки/Error! Enter one of the proposed sorting options.\n" + ex);
            return null;
        }
    }
}
