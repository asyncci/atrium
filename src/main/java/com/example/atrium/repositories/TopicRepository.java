package com.example.atrium.repositories;

import com.example.atrium.models.Topic;
import com.example.atrium.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
    List<Topic> findByUser(User user);
}
