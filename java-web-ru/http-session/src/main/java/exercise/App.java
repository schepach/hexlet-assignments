package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

            if (page == 1 && per == 5) {
                List<Map<String, String>> resultList = USERS.stream().limit(5).toList();
                System.out.println("resultList: " + resultList);
                ctx.json(resultList);
            } else {
                System.out.println("per = " + per + ", page = " + page);
                long skip = ((long) per * (page - 1));
                System.out.println("skip = " + skip);

                List<Map<String, String>> resultList = USERS.stream()
                        .skip(skip)
                        .limit(per)
                        .toList();
                System.out.println("resultList: " + resultList);
                ctx.json(resultList);
            }

        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
