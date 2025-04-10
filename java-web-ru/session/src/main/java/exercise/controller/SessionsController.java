package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var currentUser = ctx.sessionAttribute("currentUser");
        Optional<User> userOpt = UsersRepository.findByName(String.valueOf(currentUser));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            MainPage mainPage = new MainPage(user.getName());
            ctx.render("index.jte", model("page", mainPage));
            return;
        }
        ctx.render("index.jte");
    }

    public static void createSession(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        String encryptPwd = Security.encrypt(password);
        ctx.sessionAttribute("currentUser", name);

        Optional<User> optUser = UsersRepository.findByName(name);
        if (optUser.isEmpty()) {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
            return;
        }

        User user = optUser.get();
        if (!user.getName().equals(name) || !user.getPassword().equals(encryptPwd)) {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
            return;
        }

        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void login(Context ctx) {
        ctx.render("build.jte");
    }

    public static void deleteSession(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
