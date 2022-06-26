package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Comment;
import com.FurnitureKing.Project.payload.response.MessageResponse;
import com.FurnitureKing.Project.repositories.CommentRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /* Get all comments */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        return ResponseEntity.ok(commentList);
    }

    /* Create comment */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(value = "/comments/post")
    public ResponseEntity<MessageResponse> addComment(@RequestBody Comment comment) {
        comment.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        commentRepository.insert(comment);

        return ResponseEntity.ok().body(new MessageResponse("The comment is created !"));
    }

    /* Delete 1 comments */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/comments/delete/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable final String commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok().body(new MessageResponse("Comment deleted"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error : comment don't exist !"));
        }
    }

    /* Update 1 product */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/comments/put/{commentId}")
    public ResponseEntity<Optional<Comment>> updateComment(@PathVariable final String commentId, @RequestBody Comment commentUpdate) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.ifPresent(c -> {
            c.setNote(commentUpdate.getNote());
            c.setComment(commentUpdate.getComment());
            c.setUpdatedAt(CurrentDateTime.getCurrentDateTime());
            commentRepository.save(c);
        });
        return ResponseEntity.ok(comment);
    }
}
