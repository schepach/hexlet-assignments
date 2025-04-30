package exercise.daytime;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Bean name " + this.getName() + " is created!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean name " + this.getName() + " was destroyed!");
    }
    // END
}
