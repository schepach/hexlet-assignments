package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> posts(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(required = false) Integer limit) {

        if (limit == null
                || page == 0 || page < 0
                || limit == 0 || limit < 0) {
            return this.posts;
        }

        long skip = ((long) limit * page) - limit;
        return this.posts.stream()
                .skip(skip)
                .limit(limit)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable("id") String id) {
        return this.posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        this.posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post post) {
        this.posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(updP -> {
                    if (post.getTitle() != null)
                        updP.setTitle(post.getTitle());

                    if (post.getBody() != null)
                        updP.setBody(post.getBody());
                });

        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        this.posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
