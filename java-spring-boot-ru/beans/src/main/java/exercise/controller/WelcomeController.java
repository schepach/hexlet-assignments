package exercise.controller;

import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

// BEGIN
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    private final Day day;
    private final Night night;

    @Autowired
    public WelcomeController(Day day, Night night) {
        this.day = day;
        this.night = night;
    }

    @GetMapping
    public String welcome() {
        LocalDateTime now = LocalDateTime.now();
        String timesOfDay;
        if (now.getHour() >= 6 && now.getHour() <= 22) {
            timesOfDay = this.day.getName();
        } else {
            timesOfDay = this.night.getName();
        }

        return "It is " + timesOfDay + " now! Welcome to Spring!";
    }
}
// END
