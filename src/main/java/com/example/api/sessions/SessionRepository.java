package com.example.api.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    SessionRepository extends JpaRepository<Session, Integer> {

    Session getByName(String name);
}