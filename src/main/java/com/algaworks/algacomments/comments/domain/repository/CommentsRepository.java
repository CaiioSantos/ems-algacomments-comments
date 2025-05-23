package com.algaworks.algacomments.comments.domain.repository;

import com.algaworks.algacomments.comments.domain.model.CommentEntity;
import com.algaworks.algacomments.comments.domain.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CommentsRepository extends JpaRepository<CommentEntity, UUID> {
}
