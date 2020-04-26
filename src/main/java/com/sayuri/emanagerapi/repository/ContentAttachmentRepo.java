package com.sayuri.emanagerapi.repository;

import com.sayuri.emanagerapi.model.ContentAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentAttachmentRepo extends JpaRepository<ContentAttachment, Integer> {
}
