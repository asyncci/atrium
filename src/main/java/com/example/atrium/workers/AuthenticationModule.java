package com.example.atrium.workers;

import com.example.atrium.models.Session;
import com.example.atrium.models.User;
import com.example.atrium.models.UserPassword;
import com.example.atrium.models.nondb.Login;
import com.example.atrium.models.nondb.RegisterForm;
import com.example.atrium.repositories.SessionRepository;
import com.example.atrium.repositories.UserPasswordRepository;
import com.example.atrium.repositories.UserRepository;
import com.example.atrium.workers.interfaces.Authentication;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class AuthenticationModule implements Authentication<HttpStatus> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    public ResponseEntity<HttpStatus> login(String ipAddress, @RequestBody Login login){
        try{

            Optional<User> _user = userRepository.findByEmail(login.email());

            String password = login.password();
            String sha256hex = hashPassword(password);

            if(!_user.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<Session> session = sessionRepository.findByUser(_user.get());
            Optional<UserPassword> userPassword = userPasswordRepository.findByUser(_user.get());


            if(sha256hex.equals(userPassword.get().getPassword())){
                if(!session.isPresent()){
                    sessionRepository.save(new Session(ipAddress,_user.get()));
                }
                else{
                    if(session.get().getIpAddress() != ipAddress){
                        session.get().setIpAddress(ipAddress);
                        sessionRepository.save(session.get());
                        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
                    }
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception exc){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> logout(String ipAddress){
        try{
            Optional<Session> session = sessionRepository.findByIpAddress(ipAddress);

            if(session.isPresent()){
                sessionRepository.delete(session.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exc){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> register(RegisterForm user){
        try{
            Optional<User> result = userRepository
                    .findByEmail(user.getEmail());

            if(result.isPresent()){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{

                String password = user.getPassword();
                String sha256hex = hashPassword(password);

                User newUser = new User(user.getName(),user.getEmail());
                userRepository.save(newUser);
                userPasswordRepository.save(new UserPassword(sha256hex,newUser));
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        catch(Exception exc){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAccount(String ipAddress, String email){
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if(user.isPresent()){
                Optional<Session> session = sessionRepository.findByUser(user.get());

                if(session.isPresent() && session.get().getIpAddress() == ipAddress){
                    sessionRepository.delete(session.get());
                    userRepository.delete(session.get().getUser());
                }
                userRepository.delete(user.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String hashPassword(String password){
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

}
