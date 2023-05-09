package com.example.atrium.workers;

import com.example.atrium.models.Topic;
import com.example.atrium.models.User;
import com.example.atrium.repositories.TopicRepository;
import com.example.atrium.workers.interfaces.TopicsTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TopicsTrackerModule implements TopicsTracker {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public ResponseEntity<List<Topic>> topics(User user) {

        List<Topic> topics = new ArrayList<>();

        topicRepository.findByUser(user).forEach(topics::add);

        if(topics.isEmpty())
            return new ResponseEntity<>(topics,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
}
