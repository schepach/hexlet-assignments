package exercise.dto.posts;

import exercise.dto.BasePage;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildPostPage extends BasePage {
    private String name;
    private String body;
    private Map<String, List<ValidationError<Object>>> errors;
}
