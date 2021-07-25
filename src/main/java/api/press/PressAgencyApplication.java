package api.press;

import api.press.model.Actor;
import api.press.model.Admin;
import api.press.model.PostType;
import api.press.model.Role;
import api.press.repo.ActorRepo;
import api.press.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PressAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PressAgencyApplication.class, args);
    }

    @Autowired
    private ActorRepo actorRepo;
    @Autowired
    private PostRepo postRepo;
    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {

        };
    }

}
