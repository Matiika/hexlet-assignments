package exercise.controller;

import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

// BEGIN
@RestController
@Configuration
public class WelcomeController {
    @Autowired
    private Daytime daytime;

    @GetMapping("/welcome")
    public String welDay() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
// END
