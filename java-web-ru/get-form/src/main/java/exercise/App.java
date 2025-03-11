package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            System.out.println("term: " + term);
            List<User> users;
            if (term != null) {
                users = USERS.stream()
                        .filter(u -> StringUtils.startsWithIgnoreCase(u.getFirstName(), term))
                        .collect(Collectors.toList());
                System.out.println("users: " + users);

                var page = new UsersPage(users, term);
                ctx.render("users/index.jte", model("page", page));
            } else {
                System.out.println("Term value is null, show all users ...");
                var page = new UsersPage(USERS);
                ctx.render("users/index.jte", model("page", page));
            }
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
