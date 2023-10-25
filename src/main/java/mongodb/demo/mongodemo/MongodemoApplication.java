package mongodb.demo.mongodemo;

import mongodb.demo.mongodemo.models.Book;
import mongodb.demo.mongodemo.models.BookFactory;
import mongodb.demo.mongodemo.repos.BooksRepository;
import mongodb.demo.mongodemo.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {BooksRepository.class}, repositoryImplementationPostfix = "Impl")
public class MongodemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MongodemoApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "mongodb"));
        app.run(args);
    }

    @Component
    public class Init implements CommandLineRunner {
        @Autowired
        private BooksService booksService;

        @Autowired
        private BookFactory bookFactory;

        @Override
        public void run(String... args) throws Exception {
            if (booksService.countBooks() == 0) {
                List<Book> books = bookFactory.listBuilder().books(100, 100);
                booksService.saveAllBooks(books);
            }
        }
    }

    @Component
    public class CustomCode implements CommandLineRunner {
        @Autowired
        private BooksService booksService;

        @Override
        public void run(String... args) throws Exception {
            List<Book> storedBooks = booksService.getAllBooks();
            for (Book b:storedBooks) {
                System.out.println(b);
            }

            // ваш код
        }
    }
}
