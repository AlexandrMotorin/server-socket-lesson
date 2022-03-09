package org.motorin.serverSocket.lesson.servlet;

import org.motorin.serverSocket.lesson.dto.FlightDto;
import org.motorin.serverSocket.lesson.service.FlightService;
import org.motorin.serverSocket.lesson.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.util.stream.Collectors.toMap;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var flights = flightService.findAll().stream().collect(toMap(
                FlightDto::getId,
                FlightDto::getDescription
        ));

        req.setAttribute("flights", flights);

        req.getRequestDispatcher(JspHelper.getPath("flights")).forward(req,resp);
    }
}
