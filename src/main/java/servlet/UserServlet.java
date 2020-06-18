package servlet;

import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("sessionUser");

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Hello, " + user.getName() + "!!");
        resp.getWriter().println("<a href='/'>Back</a>");

    }
}
