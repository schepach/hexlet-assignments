package exercise.daytime;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {
    private String name = "night";

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
