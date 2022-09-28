package com.example.api.sessions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.List;

@Configuration
public class SessionConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerSession(SessionRepository sessionRepository) {
        return args -> {
            List<Session> sessions = List.of(
                    new Session(Name.DARK.getTitle()),
                    new Session(Name.WHITE.getTitle()),
                    new Session(Name.BLUE.getTitle()));
            List<String> names = Name.getAll();
            Iterator<Session> it1 = sessions.iterator();
            Iterator<String> it2 = names.iterator();
            while(it1.hasNext() && it2.hasNext())
            {
                Session sessionEntity = sessionRepository.getByName(it2.next());
                if (sessionEntity != null) {
                    sessionEntity.setStatus(Status.EMPTY.getTitle());
                    sessionEntity.setNumberOfPlayers(0);
                    sessionRepository.save(sessionEntity);
                } else {
                    sessionRepository.save(it1.next());
                }
            }
        };
    }
}
