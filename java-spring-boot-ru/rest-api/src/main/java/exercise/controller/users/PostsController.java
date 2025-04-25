package exercise.controller.users;

import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> posts(@PathVariable Integer id) {
        var result = posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@PathVariable Integer id, @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }
}
// END
