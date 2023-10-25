package mongodb.demo.mongodemo.services;

import mongodb.demo.mongodemo.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BooksService {
    long countBooks();
    Book createBook(Book book);
    Book getBook(String id);
    void deleteBook(String id);
    void deleteAllBooks();
    void saveAllBooks(List<Book> books);
    Page<Book> getBooks(Pageable pageable);
    List<Book> getAllBooks();
    Page<Book> findPublishedAfter(LocalDate exclusive, Pageable pageable);
    Page<Book> findBooksMatchingAll(Book probe, Pageable pageable);
}
