package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostsPage extends BasePage {

    private List<Post> posts;

}
// END
