package exercise;

import io.javalin.Javalin;
// BEGIN
import io.javalin.http.NotFoundResponse;
// END

import java.util.List;
import java.util.Map;
import java.util.Optional;


public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            System.out.println("ID = " + id);

            Optional<Map<String, String>> result = COMPANIES.stream()
                    .filter(company -> company.entrySet()
                            .stream()
                            .filter(map -> map.getKey().equals("id") && Integer.parseInt(map.getValue()) == id)
                            .findAny()
                            .map(entry -> company)
                            .isPresent())
                    .findAny();

            System.out.println("result opt: " + result);

            if (result.isPresent()) {
                System.out.println("result.get(): " + result.get());
                ctx.json(result.get());
            } else {
                throw new NotFoundResponse("Company not found");
            }
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
