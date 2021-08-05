package api.press.service;

import api.press.model.Actor;
import api.press.repo.ActorRepo;
import api.press.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActorServiceImp implements IActorService {
    private final ActorRepo actorRepo;
    private final PasswordEncoder passwordEncoder;

    public UserDetails loadUserByEmail(String email) throws UserPrincipalNotFoundException {
        return actorRepo.findByEmail(email).map(
                actor -> new User(actor.getEmail(), actor.getPassword(), new ArrayList(List.of(new SimpleGrantedAuthority(actor.getRole().getName())))))
                .orElseThrow(() -> new UserPrincipalNotFoundException("User email not found"));
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!Validator.validateEmail(username)) {
            return actorRepo.findByUsername(username).map(
                            actor -> new User(actor.getUsername(), actor.getPassword(), new ArrayList(List.of(new SimpleGrantedAuthority(actor.getRole().getName())))))
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
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
        log.info("Update user: " + actor.getUsername());
        actorRepo.update(actor).orElseThrow(()->new RuntimeException("actor with id " + actor.getId() + " is not found!"));
        return actor;
    }
}
