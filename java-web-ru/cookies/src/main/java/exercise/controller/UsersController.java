package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void save(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = Security.encrypt(ctx.formParam("password"));
        var token = Security.generateToken();

        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        Optional<User> optUser = UserRepository.getEntities().stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();

        optUser.ifPresent(u -> {
            ctx.cookie("token", token);
            ctx.redirect(NamedRoutes.userPath(u.getId()));
        });
    }

    public static void show(Context ctx) {
        var id = ctx.pathParam("id");
        String token = ctx.cookie("token");

        System.out.println("id: " + id);
        System.out.println("token: " + token);

        Optional<User> optUser = UserRepository.find(Long.parseLong(id));
        optUser.ifPresent(u -> {
            if (u.getToken().equals(token)) {
                UserPage page = new UserPage(u);
                ctx.render("users/show.jte", model("page", page));
            } else {
                ctx.redirect(NamedRoutes.buildUserPath());
            }
        });
    }
    // END
}
