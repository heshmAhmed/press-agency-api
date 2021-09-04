package api.press.service;

import api.press.model.Actor;
import api.press.repo.ActorRepo;
import api.press.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActorService implements IActorService {
    private final ActorRepo actorRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return actorRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!Validator.validateEmail(username)) {
            return actorRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        return loadUserByEmail(username);
    }

    @Override
    public Actor insert(Actor actor){
        log.info("Insert new user to database: " + actor.getUsername());
        actor.setPassword(passwordEncoder.encode(actor.getPassword()));
        actorRepo.insert(actor).orElseThrow(() -> new RuntimeException("user already exists"));
        return actor;
    }

    @Override
    public Actor update(Actor actor){
        log.info("Update user: " + actor.getId());
        actorRepo.update(actor).orElseThrow(()->new RuntimeException("actor with id " + actor.getId() + " is not found!"));
        return actor;
    }
}
