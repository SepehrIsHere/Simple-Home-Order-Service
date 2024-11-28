package org.practice.simplehomeorderservice.service;



import org.practice.simplehomeorderservice.dto.CommentDto;
import org.practice.simplehomeorderservice.entities.Comment;

import java.util.List;

public interface CommentService {
    void add(Comment comment);

    void update(Comment comment);

    void delete(Comment comment);

    List<Comment> findAll();

    Comment findById(int id);

    List<Comment> findBySpecialist(String specialistFirstName,String specialistLastName);

    Comment addAComment(CommentDto commentDto);
}
