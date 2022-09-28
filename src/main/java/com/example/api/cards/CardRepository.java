package com.example.api.cards;

import com.example.api.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByWordId(int wordId);

    Card findByGamerIdAndThemeId(Integer id, int themeId);

    List<Card> findAllByThemeId(int themeId);

    void deleteByGamerId(Integer id);

    List<Card> findAllByGamerId(int gamerId);
}
