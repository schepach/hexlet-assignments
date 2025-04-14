package exercise;

import kong.unirest.core.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    static String baseUrl;

    @BeforeAll
    static void setup() {
        baseUrl = System.getProperty("gretty.httpBaseURI");
    }

    @Test
    void testMainPage() {
        var response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Admin");
    }

    @Test
    void testHelloPage() {
        var userName = "user1";
        var response = Unirest.get(baseUrl + "/hello?name=" + userName).asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Hello, " + userName + "!");
    }

    @Test
    void testDefaultUserName() {
        var defaultName = "Guest";
        var response = Unirest.get(baseUrl + "/hello").asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("Hello, " + defaultName + "!");
    }
}
