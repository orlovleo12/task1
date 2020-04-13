package main.java.servlet;


import main.java.model.User;
import main.java.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit")
public class ServletEdit extends HttpServlet {
    UserServiceImpl userDao = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", userDao.getUserById(Integer.parseInt(req.getParameter("id"))));
        String forward = "/edit.jsp";
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("edit")) {
            User user = new User();
            user.setId(Integer.parseInt(req.getParameter("id")));
            user.setRole(req.getParameter("role"));
            user.setName(req.getParameter("name"));
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            userDao.updateUser(user);
            String forward = "/admin.jsp";

            req.setAttribute("users", userDao.getAllUsers());
            req.getRequestDispatcher(forward).forward(req, resp);

        }
    }
}