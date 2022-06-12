package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.models.Comment;
import com.FurnitureKing.Project.models.Product;
import com.FurnitureKing.Project.repositories.CommentRepository;
import com.FurnitureKing.Project.utils.CurrentDateTime;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        return ResponseEntity.ok(commentList);
    }

    /* Get all comments from 1 client*/
    @GetMapping("/comments/client/{clientId}")
    public List<Comment> getClientComments(@PathVariable final ObjectId clientId) {
        List<Comment> comments = commentRepository.findCommentByClientAndId(clientId);
        if(comments.isEmpty()){
            return (List<Comment>) ResponseEntity.notFound().build();
        }else{

            return (List<Comment>) ResponseEntity.ok(comments);
        }
    }

    /* Get all comments from 1 product*/
    @GetMapping("/comments/product/{productId}")
    public List<Comment> getProductComments(@PathVariable final ObjectId productId) {
        List<Comment> comments = commentRepository.findCommentByProductAndId(productId);
        if(comments.isEmpty()){
            return (List<Comment>) ResponseEntity.notFound().build();
        }else{

            return (List<Comment>) ResponseEntity.ok(comments);
        }
    }

    /* Create comment */
    @PostMapping(value = "/comments/post")
    public ResponseEntity<String> addComment(@RequestBody Comment comment){
        comment.setCreatedAt(CurrentDateTime.getCurrentDateTime());
        commentRepository.insert(comment);
        return ResponseEntity.ok().body("The comment is created");
    }

    /* Delete 1 comments */
    @DeleteMapping("/comments/delete/{commentsId}")
    public ResponseEntity<String> deleteProduct(@PathVariable final ObjectId commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok().body("Comment deleted");
        }
        return ResponseEntity.notFound().build();
    }

    /* Update 1 product */
    @PutMapping("/comments/put/{commentsId}")
    public ResponseEntity<Optional<Comment>> updateProduct(@PathVariable final ObjectId commentId, @RequestBody Comment commentUpdate) {
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
