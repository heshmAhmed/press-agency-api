package api.press;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * athor_github: heshmAhmed
 * version: 1
 * since: 6/6/2021
 */
@SpringBootApplication
public class PressAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PressAgencyApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
