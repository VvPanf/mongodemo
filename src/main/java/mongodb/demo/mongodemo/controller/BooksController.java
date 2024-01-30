package mongodb.demo.mongodemo.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import mongodb.demo.mongodemo.models.Book;
import mongodb.demo.mongodemo.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/books")
    public Page<Book> getBooksPage(
            @RequestParam(defaultValue = "0") @Min(0) Integer offset,              // Номер страницы
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit     // Количество элементов на странице
    ) {
        return booksService.getBooks(PageRequest.of(offset, limit));
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
