package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.ChatBotData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatBotDataRepo extends JpaRepository<ChatBotData, Integer> {
    Optional<ChatBotData> findByCode(String code);
}
