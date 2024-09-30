package demo.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void setTheAutogeneratedIdOnSave() {
        final Quote quote = repository.save(einsteinQuote());

        assertThat(quote.getId())
                .describedAs("The newly saved quote should get an autogenerated id")
                .isNotNull();
    }

    @Test
    public void listAll() {
        final Quote quote1 = repository.save(einsteinQuote());
        final Quote quote2 = repository.save(rooseveltQuote());

        final List<Quote> list = repository.findAll();

        assertThat(list)
                .describedAs("Should return all quotes in the table")
                .isEqualTo(List.of(quote1, quote2));
    }

    private static Quote einsteinQuote() {
        return new Quote("Albert Einstein", "Learn from yesterday, live for today, hope for tomorrow. The important thing is not to stop questioning.");
    }

    private static Quote rooseveltQuote() {
        return new Quote("Eleanor Roosevelt", "The future belongs to those who believe in the beauty of their dreams.");
    }
}