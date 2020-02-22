package ebookstore.service.interfaces;

import ebookstore.model.Book;
import ebookstore.model.Request;

import java.util.List;

public interface IStorageService {

    // Добавление новой книги на склад:
    void addBook(Book book);

    // Удаление книги со склада:
    void deleteBook(int idBook);

    // Изменение статуса книги:
    void editAvailableBook(int idBook, boolean available);

    // Просмотр всех книг на складе:
    List<Book> checkBooks();

    // Просмотр конкретной книги на складе:
    Book checkBook(int idBook);

    // Просмотр описания книги:
    String checkDescription(int idBook);

    // Добавление нового запроса:
    void createRequestBook(Request request);

    // Удаление запроса:
    void deleteRequestBook(int idRequest);

    // Список запросов:
    List<Request> checkRequestBooks();

    // Смена статуса запроса:
    void editStatusReq(int idRequest, String newStatus);

    // Поиск книги среди запросов:
    Integer findRequestBook(Book book);

    // Список залежавшихся книг:
    List<Book> oldBooks(/*int minusMonths*/);

    // Сортировки:
    List<Book> sortBy(String typeSort);
}
