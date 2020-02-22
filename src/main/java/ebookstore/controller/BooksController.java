package ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ebookstore.model.Book;
import ebookstore.service.interfaces.IStorageService;

import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {

    @Autowired
    private IStorageService storageService;

    @GetMapping
    public List<Book> checkBooks() {
        return storageService.checkBooks();
    }

    @GetMapping("/{id}")
    public Book checkBook(@PathVariable("id") int idBook) {
        return storageService.checkBook(idBook);
    }

    @GetMapping("/description")
    public String checkDescription(@RequestParam("idBook") int idBook) {
        return storageService.checkDescription(idBook);
    }

    @GetMapping("/oldBooks")
    public List<Book> oldBooks() {
        return storageService.oldBooks();
    }

    @GetMapping("/sortedBooks")
    public List<Book> sortBooksBy(@RequestParam("typeSort") String typeSort) {
        return storageService.sortBy(typeSort);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        storageService.addBook(book);
    }

    @PutMapping("/{id}")
    public void editAvailableBook(@PathVariable("id") int idBook, @RequestBody boolean available) {
        storageService.editAvailableBook(idBook, available);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int idBook) {
        storageService.deleteBook(idBook);
    }
}
