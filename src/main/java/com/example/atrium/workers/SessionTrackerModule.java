package com.example.atrium.workers;

import com.example.atrium.models.Session;
import com.example.atrium.models.User;
import com.example.atrium.repositories.SessionRepository;
import com.example.atrium.workers.interfaces.SessionTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SessionTrackerModule implements SessionTracker {
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public ResponseEntity<User> isSessionGoing(String ipAddress) {
        Optional<Session> session = sessionRepository.findByIpAddress(ipAddress);
        if(session.isPresent()){
            return new ResponseEntity<>(session.get().getUser(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
