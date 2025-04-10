package exercise;

import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get(NamedRoutes.rootPath(), SessionsController::index);
        app.get(NamedRoutes.buildSessionPath(), SessionsController::login);
        app.post(NamedRoutes.loginPath(), SessionsController::createSession);
        app.post(NamedRoutes.logoutPath(), SessionsController::deleteSession);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
