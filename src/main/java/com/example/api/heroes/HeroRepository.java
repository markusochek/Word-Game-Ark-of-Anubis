package com.example.api.heroes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Optional<Hero> findByName(String name);

    boolean existsByName(String name);
}
