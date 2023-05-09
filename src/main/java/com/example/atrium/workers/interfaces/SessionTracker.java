package com.example.atrium.workers.interfaces;

import com.example.atrium.models.User;
import org.springframework.http.ResponseEntity;

public interface SessionTracker<T> {
    ResponseEntity<T> isSessionGoing(String ipAddress);
}
