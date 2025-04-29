package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> index() {
        return this.commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        Optional<Comment> optComment = this.commentRepository.findById(id);
        if (optComment.isEmpty()) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found!");
        }
        return optComment.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return this.commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> optComment = this.commentRepository.findById(id);
        if (optComment.isEmpty()) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found!");
        }

        Comment updComment = optComment.get();
        updComment.setBody(comment.getBody());
        return this.commentRepository.save(updComment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.commentRepository.deleteById(id);
    }
}
// END
