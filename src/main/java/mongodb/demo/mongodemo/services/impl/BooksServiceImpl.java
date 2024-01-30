package mongodb.demo.mongodemo.services.impl;

import mongodb.demo.mongodemo.models.Book;
import mongodb.demo.mongodemo.repos.BooksRepository;
import mongodb.demo.mongodemo.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepo;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepo) {
        this.booksRepo = booksRepo;
    }

    @Override
    public long countBooks() {
        return booksRepo.count();
    }

    @Override
    public Book createBook(Book book) {
        booksRepo.save(book);
        return book;
    }

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        Page<Book> books = booksRepo.findAll(pageable);
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return booksRepo.findAll();
    }

    @Override
    public Book getBook(String id) {
        return booksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга с id=" + id + " не найдена"));
    }

    @Override
    public void deleteBook(String id) {
        booksRepo.deleteById(id);
    }

    @Override
    public Page<Book> findPublishedAfter(LocalDate afterDate, Pageable pageable) {
        Page<Book> books = booksRepo.findByPublishedAfter(afterDate, pageable);
        return books;
    }

    @Override
    public void deleteAllBooks() {
        booksRepo.deleteAll();
    }

    @Override
    public void saveAllBooks(List<Book> books) {
        booksRepo.saveAll(books);
    }

    @Override
    public Page<Book> findBooksMatchingAll(Book probe, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        Page<Book> books = booksRepo.findAll(Example.of(probe, matcher), pageable);
        return books;
    }
}

