package com.example.api.riddles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiddleRepository extends JpaRepository<Riddle, Integer> {
    Optional<Riddle> findByGamerIdAndThemeId(int gamerId, int themeId);

    void deleteByGamerId(Integer id);

    List<Riddle> findAllByGamerId(Integer id);
}
