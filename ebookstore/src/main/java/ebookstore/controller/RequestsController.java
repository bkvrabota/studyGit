package ebookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ebookstore.model.Book;
import ebookstore.model.Request;
import ebookstore.service.interfaces.IStorageService;

import java.util.List;

@RestController
@RequestMapping("requests")
public class RequestsController {

    @Autowired
    private IStorageService storageService;

    @GetMapping
    public List<Request> checkRequestBooks() {
        return storageService.checkRequestBooks();
    }

    @GetMapping("/requestBook")
    public Integer findRequestBook(@RequestBody Book book) {
        return storageService.findRequestBook(book);
    }

    @PostMapping
    public void createRequestBook(@RequestBody Request request) {
        storageService.createRequestBook(request);
    }

    @PutMapping("/{id}")
    public void editStatusReq(@PathVariable("id") int idRequest, @RequestBody String newStatus) {
        storageService.editStatusReq(idRequest, newStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestBook(@PathVariable("id") int idRequest) {
        storageService.deleteRequestBook(idRequest);
    }
}
