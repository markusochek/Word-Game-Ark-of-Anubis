package com.example.api.gamers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Integer> {

    Optional<Gamer> findByUserId(Integer id);

    List<Gamer> findAllBySessionId(int sessionId);
}
