package exercise.util;

import exercise.model.User;

import java.util.ArrayList;
import java.util.List;

import static exercise.util.Security.encrypt;

public class Generator {

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1L, "admin", encrypt("secret")));
        users.add(new User(2L, "mike", encrypt("superpass")));
        users.add(new User(3L, "kate", encrypt("strongpass")));

        return users;
    }
}
