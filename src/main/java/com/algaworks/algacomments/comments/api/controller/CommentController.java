package com.algaworks.algacomments.comments.api.controller;

import com.algaworks.algacomments.comments.api.model.CommentInput;
import com.algaworks.algacomments.comments.api.model.CommentOutput;
import com.algaworks.algacomments.comments.domain.model.CommentEntity;
import com.algaworks.algacomments.comments.domain.model.CommentId;
import com.algaworks.algacomments.comments.domain.repository.CommentsRepository;
import com.algaworks.algacomments.comments.domain.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentsService commentsService;

    @PostMapping
    public CommentOutput create(@RequestBody CommentInput input) {
        return commentsService.create(input);
    }

    @GetMapping("/{id}")
    public CommentOutput create(@PathVariable String id) throws Exception {
        return commentsService.getCommentById(UUID.fromString(id));
    }

    @GetMapping
    public Page<CommentOutput> create(Pageable pageable) {
        return commentsService.getAllCommentsPage(pageable);
    }
}
