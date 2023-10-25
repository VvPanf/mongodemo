package mongodb.demo.mongodemo.controller;

import mongodb.demo.mongodemo.models.Book;
import mongodb.demo.mongodemo.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBooks(@PathVariable String id) {
        return booksService.getBook(id);
    }

    @PostMapping("/books")
    public Book postBook(@RequestBody Book book) {
        return booksService.createBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        booksService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book putBook(@RequestBody Book newBook, @PathVariable String id) {
        newBook.setId(id);
        return booksService.createBook(newBook);
    }
}
