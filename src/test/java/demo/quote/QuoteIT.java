package demo.quote;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteIT {

    @Test
    void fetchRandomQuote() {
        final RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl())
                .build();

        final Quote body = restClient.get()
                .uri("/quote/random")
                .retrieve()
                .body(Quote.class);

        assertThat(body).isNotNull();
        assertThat(body.getId()).isNotNegative();
        assertThat(body.getAuthor()).isNotEmpty();
        assertThat(body.getQuote()).isNotEmpty();
    }

    private static String baseUrl() {
        final String serverPort = System.getProperty("test.server.port");
        assertThat(serverPort)
                .describedAs("Missing server port")
                .isNotEmpty();

        return "http://localhost:".concat(serverPort);
    }
}
