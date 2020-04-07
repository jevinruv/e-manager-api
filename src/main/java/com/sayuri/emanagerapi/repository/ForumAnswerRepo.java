package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.ForumAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumAnswerRepo extends JpaRepository<ForumAnswer, Integer> {
}
