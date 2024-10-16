package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN

    @PostConstruct
    public void init() {
        System.out.println("Bean is initialized!");
    }

    // END
}
