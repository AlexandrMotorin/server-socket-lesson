package org.motorin.serverSocket.lesson.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebServlet("/first")
public class MyFirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var writer = resp.getWriter();
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        writer.write("<h1>Headers of request</h1><br><br>");

        var headerNames = req.getHeaderNames();

        while (headerNames.hasMoreElements()){
            var headerName = headerNames.nextElement();
            var headerValue = req.getHeader(headerName);
            writer.write("<p>" + headerNames.nextElement() + " : " + headerValue + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var parameterMap = req.getParameterMap();
    }
}
