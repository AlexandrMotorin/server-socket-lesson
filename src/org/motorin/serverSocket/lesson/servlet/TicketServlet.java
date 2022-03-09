package org.motorin.serverSocket.lesson.servlet;

import org.motorin.serverSocket.lesson.service.TicketService;
import org.motorin.serverSocket.lesson.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var flightId = Long.valueOf(req.getParameter("flightId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        req.setAttribute("tickets", ticketService.getAllByFlightId(flightId));
        req.getRequestDispatcher(JspHelper.getPath("tickets")).forward(req,resp);
    }
}
