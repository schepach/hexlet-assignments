package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    public static String postsPath() {
        return "/posts/";
    }

    public static String postsPathByPage(Integer page) {
        return postsPathByPage(String.valueOf(page));
    }

    public static String postsPathByPage(String page) {
        return "/posts?page=" + page;
    }
    // END
}
