package com.example.api.words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    boolean existsByWord(String word);
    List<Word> findAllByThemeId(int themeId);

    Word findById(int wordId);
}
