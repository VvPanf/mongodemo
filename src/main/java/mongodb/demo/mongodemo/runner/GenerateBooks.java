package mongodb.demo.mongodemo.runner;

import com.github.javafaker.Faker;
import mongodb.demo.mongodemo.models.Book;
import mongodb.demo.mongodemo.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Раскоментировать аннотацию для генерации записей
//@Component
public class GenerateBooks implements CommandLineRunner {
    private static final int LIST_SIZE = 1000;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private BooksService booksService;

    @Override
    public void run(String... args) throws Exception {
        booksService.saveAllBooks(generateBooks());
    }

    private List<Book> generateBooks() throws ParseException {
        Faker faker = new Faker();
        List<Book> books = new ArrayList<>(LIST_SIZE);
        for (int i=0; i<LIST_SIZE; i++) {
            books.add(new Book(
                    faker.book().title(),
                    faker.book().author(),
                    toLocalDate(faker.date().between(
                        formatter.parse("1900-01-01"),
                        formatter.parse("2000-01-01"))
                    )
            ));
        }
        return books;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
