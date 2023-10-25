package mongodb.demo.mongodemo.models;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BookFactory {
    private static AtomicInteger nextId = new AtomicInteger(1);
    private static AtomicLong nextEpochDay = new AtomicLong(
            LocalDate.of(2000, 1, 1).toEpochDay());
    private final Faker faker = new Faker();
    public String author() { return faker.book().author(); }
    public String title() { return faker.book().title() + " " + faker.color().name(); }
    public LocalDate published() {
        return faker.date()
                .past(20*365, TimeUnit.DAYS)
                .toInstant()
                .atOffset(ZoneOffset.UTC)
                .toLocalDate();
    }

    public Book make(UnaryOperator<Book>...books) {
        final Book result = new Book(title(), author(),published());
        Stream.of(books).forEach(v->v.apply(result));
        return result;
    }

    public static UnaryOperator<Book> oneUpId = s->{
        s.setId(Integer.valueOf(nextId.getAndAdd(1)).toString());
        return s;
    };

    public static UnaryOperator<Book> nextDate = s->{
        LocalDate ld = LocalDate.ofEpochDay(nextEpochDay.getAndAdd(1L));
        s.setPublished(ld);
        return s;
    };

    public BooksListDTOFactory listBuilder() { return new BooksListDTOFactory(); }

    public class BooksListDTOFactory {
        public List<Book> books(int min, int max, UnaryOperator<Book>...books) {
            return IntStream.range(0, faker.number().numberBetween(min, max))
                    .mapToObj(i-> BookFactory.this.make(books))
                    .collect(Collectors.toList());
        }
    }
}
