package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostsController(PostRepository postRepository,
                           CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Post> index() {
        return this.postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        Optional<Post> optPost = this.postRepository.findById(id);
        if (optPost.isEmpty()) {
            throw new ResourceNotFoundException("Post with id " + id + " not found!");
        }
        return optPost.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return this.postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> optPost = this.postRepository.findById(id);
        if (optPost.isEmpty()) {
            throw new ResourceNotFoundException("Post with id " + id + " not found!");
        }

        Post updPost = optPost.get();
        updPost.setTitle(post.getTitle());
        updPost.setBody(post.getBody());
        return this.postRepository.save(updPost);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.postRepository.deleteById(id);
        this.commentRepository.deleteByPostId(id);
    }


}
// END
