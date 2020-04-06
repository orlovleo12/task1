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

@WebServlet("/list")
public class ServletList extends HttpServlet {
    private static String LIST_USER = "/listUser.jsp";
    private static String EDIT = "/edit.jsp";
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        request.setAttribute("users", service.getAllUsers());
        String action = request.getParameter("action");


            forward = LIST_USER;


        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
}
