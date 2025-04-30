package exercise;

// BEGIN
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Day day() {
        return new Day();
    }

    @Bean
    @RequestScope
    public Night night() {
        return new Night();
    }
    // END
}
