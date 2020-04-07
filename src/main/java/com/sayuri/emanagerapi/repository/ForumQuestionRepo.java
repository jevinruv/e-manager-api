package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.ForumQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumQuestionRepo extends JpaRepository<ForumQuestion, Integer> {
}
