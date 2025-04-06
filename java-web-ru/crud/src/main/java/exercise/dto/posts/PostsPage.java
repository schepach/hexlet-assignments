package exercise.dto.posts;

import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


// BEGIN
@Data
@AllArgsConstructor
public class PostsPage {

    private List<Post> posts;
    private int previousPage;
    private int nextPage;

}
// END


