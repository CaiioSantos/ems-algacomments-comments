package com.algaworks.algacomments.comments.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class CommentId implements Serializable {

    private UUID value;

    public CommentId(UUID value){
        this.value= value;
    }

    public CommentId(String value){
        this.value= UUID.fromString(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
