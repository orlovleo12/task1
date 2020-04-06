package main.java.servlet;

import main.java.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delete")
public class ServletDelete extends HttpServlet {
    private static String LIST_USER = "/listUser.jsp";
    UserServiceImpl userDao = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            userDao.deleteUser(userId);
            forward = LIST_USER;
            req.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
