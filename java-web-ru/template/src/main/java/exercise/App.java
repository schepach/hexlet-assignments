package exercise;

import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            USERS.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .ifPresentOrElse(user -> {
                                var userPage = new UserPage(user);
                                ctx.render("users/show.jte", model("userPage", userPage));
                            },
                            () -> {
                                throw new NotFoundResponse("User not found");
                            });
        });

        app.get("/users", ctx -> {
            var users = new UsersPage(USERS);
            System.out.println("users: " + users.getUsers());
            ctx.render("users/index.jte", model("usersPage", users));
        });

        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
