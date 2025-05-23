package com.algaworks.algacomments.comments.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class CommentOutput {

    private UUID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;


}
