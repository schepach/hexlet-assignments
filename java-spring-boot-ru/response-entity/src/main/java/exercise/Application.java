package exercise;

import exercise.model.Post;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> posts() {
        var result = this.posts;
        return ResponseEntity
                .ok()
                .header("X-Total-Count", String.valueOf(result.size()))
                .body(result);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        var result = this.posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (result.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result.get());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        this.posts.add(post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> optPost = this.posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (optPost.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Post updP = optPost.get();
        if (post.getTitle() != null)
            updP.setTitle(post.getTitle());

        if (post.getBody() != null)
            updP.setBody(post.getBody());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(post);
    }

    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
