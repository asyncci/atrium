package com.example.atrium.workers.interfaces;

import com.example.atrium.models.Topic;
import com.example.atrium.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicsTracker {
    ResponseEntity<List<Topic>> topics(User user);
}
