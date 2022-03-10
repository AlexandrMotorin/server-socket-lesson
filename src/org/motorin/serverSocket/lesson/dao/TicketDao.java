package org.motorin.serverSocket.lesson.dao;

import org.motorin.serverSocket.lesson.entity.Ticket;
import org.motorin.serverSocket.lesson.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements Dao<Long, Ticket>{

    private static final TicketDao INSTANCE = new TicketDao();

    public static final String FIND_ALL_BY_FLIGHT_ID = """
            SELECT *
            FROM ticket
            where flight_id = ?
            """;

    public static TicketDao getInstance(){
        return INSTANCE;
    }

    public List<Ticket> findAllByFlightId(Long flightId){
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID)){

            preparedStatement.setObject(1,flightId);

            var resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }

            return tickets;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }

    private TicketDao() {
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Ticket save(Ticket ticket) {
        return null;
    }
}
