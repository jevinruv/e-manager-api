package com.sayuri.emanagerapi.controller;

import com.sayuri.emanagerapi.model.ChatBotData;
import com.sayuri.emanagerapi.repository.ChatBotDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat-bot")
public class ChatBotDataController {

    @Autowired
    private ChatBotDataRepo repo;

    @GetMapping("/{id}")
    public Optional<ChatBotData> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<ChatBotData> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ChatBotData addOrUpdate(@RequestBody ChatBotData chatBotData) {
        return repo.save(chatBotData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }

}
