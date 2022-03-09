package org.motorin.serverSocket.lesson.service;

import org.motorin.serverSocket.lesson.dao.FlightDao;
import org.motorin.serverSocket.lesson.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService(){

    }

    public List<FlightDto> findAll() {

        var all = flightDao.findAll();
        return all.stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        """
                           %s - %s - %s
                        """.formatted(flight.getDepartureAirportCode(),flight.getArrivalAirportCode(),flight.getStatus())))
                .collect(toList());
    }

    public static FlightService getInstance(){
        return INSTANCE;
    }
}
