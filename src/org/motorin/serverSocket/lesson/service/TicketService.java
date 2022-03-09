package org.motorin.serverSocket.lesson.service;

import org.motorin.serverSocket.lesson.dao.TicketDao;
import org.motorin.serverSocket.lesson.dto.TicketDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();

    private final TicketDao ticketDao = TicketDao.getInstance();

    public static TicketService getInstance(){
        return INSTANCE;
    }

    private TicketService(){
    }

    public List<TicketDto> getAllByFlightId(Long id){
        return ticketDao.findAllByFlightId(id).stream()
                .map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlightId(),
                        ticket.getSeatNo()
                ))
                .collect(toList());
    }
}
