package demo.quote;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

import static java.util.Objects.requireNonNull;

@Service
public class QuoteService {

    private final QuoteRepository repository;

    public QuoteService(final QuoteRepository repository) {
        this.repository = requireNonNull(repository);
    }

    public Optional<Quote> random() {
        /* Returns a random quote using, inefficiently, by loading all the
            quotes from the database and then picking one at random. A more
            efficient way would have fetched the count of quotes in the table
            and then return the one found at a random index using the SQL
            LIMIT and OFFSET. */
        final List<Quote> list = repository.findAll();

        if (list.isEmpty()) {
            return Optional.empty();
        }

        final RandomGenerator random = RandomGenerator.getDefault();
        final int index = random.nextInt(list.size());
        return Optional.of(list.get(index));
    }
}
