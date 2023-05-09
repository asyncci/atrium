package com.example.atrium.controllers.api;

import com.example.atrium.models.User;
import com.example.atrium.models.nondb.Login;
import com.example.atrium.models.nondb.RegisterForm;
import com.example.atrium.workers.interfaces.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    Authentication authenticationModule;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<HttpStatus> login(HttpServletRequest request, @RequestBody Login login){

        String ipAddress = request.getRemoteAddr();
        return authenticationModule.login(ipAddress,login);

    }

    @DeleteMapping("/login")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        return authenticationModule.logout(ipAddress);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody RegisterForm user){
        return authenticationModule.register(user);
    }


    @DeleteMapping("/register")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteAccount(HttpServletRequest request,@RequestParam("email") String email){
        String ipAddress = request.getRemoteAddr();
        return authenticationModule.deleteAccount(ipAddress, email);
    }

}
