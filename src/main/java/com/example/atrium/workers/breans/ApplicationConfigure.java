package com.example.atrium.workers.breans;

import com.example.atrium.workers.AuthenticationModule;
//import com.example.atrium.workers.SessionTrackerModule;
import com.example.atrium.workers.SessionTrackerModule;
import com.example.atrium.workers.TopicsTrackerModule;
import com.example.atrium.workers.interfaces.Authentication;
import com.example.atrium.workers.interfaces.SessionTracker;
import com.example.atrium.workers.interfaces.TopicsTracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ApplicationConfigure {

    @Bean
    public Authentication<HttpStatus> authenticationModule(){
        return new AuthenticationModule();
    }

    @Bean
    public SessionTracker sessionTracker() { return new SessionTrackerModule(); }

    @Bean
    public TopicsTracker topicsTracker() { return new TopicsTrackerModule(); };

}
