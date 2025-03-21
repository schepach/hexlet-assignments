package exercise.dto.articles;

import exercise.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ArticlesPage {
    private List<Article> articles;
}
