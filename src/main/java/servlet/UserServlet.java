package main.java.servlet;

import main.java.dao.UserDaoJDBCImpl;
import main.java.factory.DBHelper;
import main.java.model.User;
import main.java.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static String LIST_USER = "/listUser.jsp";
    private static String EDIT = "/edit.jsp";
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        request.setAttribute("users", service.getAllUsers());
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            service.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", service.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = service.getUserById(userId);
            forward = EDIT;
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("add")) {
            forward = "/add.jsp";
        } else {
            forward = LIST_USER;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("edit")) {
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setName(request.getParameter("name"));
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            service.updateUser(user);
            String forward = LIST_USER;

            request.setAttribute("users", service.getAllUsers());
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("add")) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            service.addUser(user);

            String forward = LIST_USER;
            request.setAttribute("users", service.getAllUsers());
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }
}
