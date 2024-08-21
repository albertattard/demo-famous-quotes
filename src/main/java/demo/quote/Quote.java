package demo.quote;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String quote;

    protected Quote() {}

    public Quote(final long id, final String author, final String quote) {
        this.id = id;
        this.author = author;
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Quote other
                && Objects.equals(id, other.id)
                && Objects.equals(author, other.author)
                && Objects.equals(quote, other.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, quote);
    }

    @Override
    public String toString() {
        return "Quote[id=%d, author=%s, quote=%s]".formatted(id, author, quote);
    }
}
