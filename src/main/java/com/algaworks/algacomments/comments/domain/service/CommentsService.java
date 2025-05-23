package com.algaworks.algacomments.comments.domain.service;

import com.algaworks.algacomments.comments.api.client.ModerationClient;
import com.algaworks.algacomments.comments.api.model.CommentInput;
import com.algaworks.algacomments.comments.api.model.CommentOutput;
import com.algaworks.algacomments.comments.api.model.ModerationRequest;
import com.algaworks.algacomments.comments.api.model.ModerationResponse;
import com.algaworks.algacomments.comments.domain.CommentNotFoundException;
import com.algaworks.algacomments.comments.domain.model.CommentEntity;
import com.algaworks.algacomments.comments.domain.model.CommentId;
import com.algaworks.algacomments.comments.domain.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final ModerationClient moderationClient;

    public CommentOutput create(CommentInput input){
        var entity =  CommentEntity.builder()
                .id(UUID.randomUUID())
                .text(input.getText())
                .author(input.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();
        ModerationRequest request = new ModerationRequest(entity.getId(), entity.getText());
        ModerationResponse response = moderationClient.moderateComment(request);

        if (!response.isApproved()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return this.mapToOutput(commentsRepository.saveAndFlush(entity));
    }
    public CommentOutput getCommentById(UUID id) throws Exception {
        CommentEntity comment = commentsRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return mapToOutput(comment);
    }

    private CommentOutput mapToOutput(CommentEntity comment) {
        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public Page<CommentOutput> getAllCommentsPage(Pageable pageable){
        Page<CommentEntity> listEntity = commentsRepository.findAll(pageable);
        return listEntity.map(this::mapToOutput);
    }

}
