package main.java.servlet;

import main.java.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String denURI = request.getContextPath() + "/block";
        User user;

        boolean loggedIn = session != null && session.getAttribute("user") != null;

            if (loggedIn) {
                user = (User) session.getAttribute("user");
                if (user.getRole().equals("admin")) {
                    chain.doFilter(request, response);
                } else if (user.getRole().equals("user")) {
                    if (request.getRequestURI().contains("/admin")) {
                        response.sendRedirect(denURI);
                    } else {
                        chain.doFilter(request, response);
                    }
                }
            } else {
                chain.doFilter(request, response);
            }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

}