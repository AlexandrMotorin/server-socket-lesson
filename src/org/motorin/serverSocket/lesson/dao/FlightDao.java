package org.motorin.serverSocket.lesson.dao;

import org.motorin.serverSocket.lesson.entity.Flight;
import org.motorin.serverSocket.lesson.entity.FlightStatus;
import org.motorin.serverSocket.lesson.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao implements Dao<Long, Flight>{

    private static final FlightDao INSTANCE = new FlightDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM flight
            """;

    private FlightDao() {
    }

    @Override
    public List<Flight> findAll() {
        try(var connection = ConnectionManager.get()){
            var preparedStatement = connection.prepareStatement(FIND_ALL);
            var resultSet = preparedStatement.executeQuery();

            List<Flight> flights = new ArrayList<>();

            while (resultSet.next()){
                flights.add(buildFlight(resultSet));
            }
            return flights;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }

    public static FlightDao getInstance(){
        return INSTANCE;
    }
}
