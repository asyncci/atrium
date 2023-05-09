package com.example.atrium.workers.interfaces;

import com.example.atrium.models.nondb.RegisterForm;
import org.springframework.http.ResponseEntity;

import com.example.atrium.models.User;
import com.example.atrium.models.nondb.Login;
import org.springframework.web.bind.annotation.*;

public interface Authentication<T> {

    ResponseEntity<T> login(String ipAddress, @RequestBody Login login);

    ResponseEntity<T> logout(String ipAddress);

    ResponseEntity<T> register(RegisterForm user);

    ResponseEntity<T> deleteAccount(String ipAddress, String email);

}
