package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        Optional<Post> optPost = PostRepository.find(id);
        if (optPost.isEmpty()) {
            throw new NotFoundResponse("Page not found.");
        }

        Post post = optPost.get();
        ctx.render("posts/show.jte", model("page", new PostPage(post)));
    }

    public static void showAllPosts(Context ctx) {
        var page = ctx.queryParamAsClass("page", Integer.class)
                .getOrDefault(1);

        if (page == 0 || page < 0)
            page = 1;

        var previousPage = (page - 1 == 0 ? 1 : page - 1);
        var nextPage = page + 1;

        System.out.println("previousPage = " + previousPage);
        System.out.println("nextPage = " + nextPage);
        List<Post> posts = PostRepository.findAll(page, 5);

        if (posts.isEmpty()) {
            ctx.redirect(NamedRoutes.postsPath());
            return;
        }

        ctx.render("posts/index.jte", model("page",
                new PostsPage(posts,
                        previousPage,
                        nextPage)));
    }
    // END
}
