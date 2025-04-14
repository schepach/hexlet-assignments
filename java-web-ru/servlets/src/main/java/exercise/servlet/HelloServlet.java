package exercise.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "Guest";
        }
        req.setAttribute("name", name);
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }
    // END
}
