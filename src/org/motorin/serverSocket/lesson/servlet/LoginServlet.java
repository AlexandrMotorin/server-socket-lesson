package org.motorin.serverSocket.lesson.servlet;

import lombok.SneakyThrows;
import org.motorin.serverSocket.lesson.dto.UserDto;
import org.motorin.serverSocket.lesson.service.UserService;
import org.motorin.serverSocket.lesson.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"),req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(req,resp,user),
                        () -> onLoginFail(req,resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email="+req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(HttpServletRequest req, HttpServletResponse resp, UserDto user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/flights");
    }
}
