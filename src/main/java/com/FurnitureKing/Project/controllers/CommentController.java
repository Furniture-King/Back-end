package com.FurnitureKing.Project.controllers;

import com.FurnitureKing.Project.repositories.CommentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //FAIRE LE CRUD
}
