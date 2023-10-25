package mongodb.demo.mongodemo.config;

import mongodb.demo.mongodemo.models.BookFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BooksConfiguration {
    @Bean
    public BookFactory booksDtoFactory() {
        return new BookFactory();
    }
}
