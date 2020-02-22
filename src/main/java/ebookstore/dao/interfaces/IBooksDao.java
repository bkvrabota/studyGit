package ebookstore.dao.interfaces;

import ebookstore.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface IBooksDao extends IGenericDAO<Book> {

    List<Book> getOldBooks(LocalDate start, LocalDate end);
}
