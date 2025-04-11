package exercise.controller;

import exercise.dto.BasePage.FlashType;
import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        String flash = ctx.consumeSessionAttribute("flash");
        var flashType = ctx.consumeSessionAttribute("flashType");

        List<Post> posts = PostRepository.getEntities();
        PostsPage page = new PostsPage(posts);

        if (flashType != null && flash != null) {
            page.setFlash(flash);
            page.setFlashType((FlashType) flashType);
        }
        ctx.render("posts/index.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(val -> val.length() >= 2, "Название не должно быть короче двух символов!")
                    .get();
            String body = ctx.formParamAsClass("body", String.class).get();

            Post post = new Post(name, body);
            PostRepository.save(post);

            ctx.sessionAttribute("flash", "Post was successfully created!");
            ctx.sessionAttribute("flashType", FlashType.success);
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException ex) {
            String name = ctx.formParamAsClass("name", String.class).get();
            String body = ctx.formParamAsClass("body", String.class).get();
            BuildPostPage page = new BuildPostPage(name, body, ex.getErrors());
            page.setFlash("Validation error! Name must be more than 2 letters!");
            page.setFlashType(FlashType.danger);
            ctx.sessionAttribute("flashType", page.getFlashType());
            ctx.render("posts/build.jte", model("page", page))
                    .status(HttpStatus.UNPROCESSABLE_CONTENT);
        }
    }

    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
