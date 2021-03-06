package api.press.service;

import api.press.model.Actor;
import api.press.repo.IRepo.IActorRepo;
import api.press.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActorService implements IActorService {
    private final IActorRepo actorRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return actorRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<Actor> getAllActors() {
        return this.actorRepo.getAllActors();
    }

    @Override
    public void deleteActor(Integer actorId) throws RuntimeException {
       int rs = this.actorRepo.delete(actorId);
       if(rs == 0)
           throw new RuntimeException("User not found!");
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!Validator.validateEmail(username)) {
            return actorRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        }
        return loadUserByEmail(username);
    }

    @Override
    public Actor createActor(Actor actor) throws RuntimeException {
        log.info("Insert new user to database: " + actor.getUsername());
        actor.setPassword(passwordEncoder.encode(actor.getPassword()));
        actorRepo.insert(actor).orElseThrow(() -> new RuntimeException("User already exists!"));
        return actor;
    }

    @Override
    public void updateActor(Actor actor) throws RuntimeException {
        int rs;
        try {
            rs = actorRepo.update(actor);
        }catch (DuplicateKeyException e){
            throw new RuntimeException("Email is used!");
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Bad body content!");
        }
        if(rs != 1)
            throw new RuntimeException("User with id " + actor.getId() + " not exists!");
    }
}
