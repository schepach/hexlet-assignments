package exercise.util;

import exercise.model.Post;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Generator {
    private static final int ITEMS_COUNT = 4;
    private static final Random RANDOM = new Random(123);

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        Faker faker = new Faker(new Locale("en"), RANDOM);

        for (int i = 0; i < ITEMS_COUNT; i++) {
            var name = faker.book().title();
            var body = faker.lorem().sentence();
            var id = faker.number().randomNumber();
            var post = new Post(id, name, body);
            posts.add(post);
        }

        return posts;
    }

}
