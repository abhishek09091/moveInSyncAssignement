package com.example.moveinSync.moveInSync.serviceImpl;

import com.example.moveinSync.moveInSync.model.Address;
import com.example.moveinSync.moveInSync.model.Theater;
import com.example.moveinSync.moveInSync.service.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JDBCTheaterRepository implements TheaterRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource  dataSource;

    public JDBCTheaterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Theater create(Theater theater) throws Exception {
        Address address = theater.getAddress();
        Connection connection = null;
        PreparedStatement preparedStatementAddress = null;
        PreparedStatement preparedStatement = null;
        if(address == null){
            return null;
        }
         String SQL_INSERT_THEATER = "INSERT INTO theater_entries (theater_id, theater_name, address_id ,rating) VALUES (?,?,?,?)";
         String SQL_INSET_ADDRESS = "INSERT INTO address (address_id,city,pin_code,state,landmark,street_no) VALUES (?,?,?,?,?,?)";
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatementAddress = connection.prepareStatement(SQL_INSET_ADDRESS);

            preparedStatementAddress.setInt(1,address.getAddressId());
            preparedStatementAddress.setString(2,address.getCity());
            preparedStatementAddress.setString(3,address.getPinCode());
            preparedStatementAddress.setString(4,address.getState());
            preparedStatementAddress.setString(5,address.getLandmark());
            preparedStatementAddress.setString(6,address.getStreetNo());
            int row_address = preparedStatementAddress.executeUpdate();
            System.out.println(row_address);

            preparedStatement = connection.prepareStatement(SQL_INSERT_THEATER);
            preparedStatement.setLong(1,theater.getTheaterId());
            preparedStatement.setString(2,theater.getTheaterName());
            preparedStatement.setFloat(4,theater.getRating());
            preparedStatement.setInt(3,theater.getAddress().getAddressId());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return null;
        }
        finally {

            if (preparedStatementAddress != null) {
                preparedStatementAddress.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }

        }
        return new Theater(theater.getTheaterId(),theater.getTheaterName(),theater.getAddress(),theater.getRating());
    }

    @Override
    public Theater find(Long id) throws Exception {
        String SQL_FIND_THEATER_ADDRESS = "select * from address , theater_entries where address.address_id = theater_entries.address_id and theater_entries.theater_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement addressStatement = null;
        Theater theater = null;
        Address address = null;
        try{

            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_FIND_THEATER_ADDRESS);
            statement.setLong(1,id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                theater = new Theater();
                address = new Address();
                theater.setTheaterName(rs.getString("theater_name"));
                theater.setRating(rs.getFloat("rating"));
                theater.setTheaterId(rs.getLong("theater_id"));
                address.setAddressId(rs.getInt("address_id"));
                address.setCity(rs.getString("city"));
                address.setPinCode(rs.getString("pin_code"));
                address.setState(rs.getString("state"));
                address.setLandmark(rs.getString("landmark"));
                address.setStreetNo(rs.getString("street_no"));
                theater.setAddress(address);
            }
            connection.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return null;
        }finally {
            if(statement != null){
                statement.close();
            }
            if(addressStatement != null){
                addressStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return theater;
    }

    @Override
    public List<Theater> list() {
        return null;
    }

    @Override
    public Theater update(Long id, Theater theater) throws Exception {
        String SQL_UPDATE_THEATER = "update theater_entries set theater_name = ? , rating = ? where theater_id = ?";
        String SQL_UPDATE_ADDRESS = "update address set city = ? , pin_code = ? , state = ? , landmark = ? , street_no = ? where address_id = ? ";
        Theater findTheater = find(id);
        if(findTheater == null || theater == null){
            return null;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement addressStatement = null;

        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            addressStatement = connection.prepareStatement(SQL_UPDATE_ADDRESS);
            addressStatement.setString(1,theater.getAddress().getCity());
            addressStatement.setString(2,theater.getAddress().getPinCode());
            addressStatement.setString(3,theater.getAddress().getState());
            addressStatement.setString(4,theater.getAddress().getLandmark());
            addressStatement.setString(5,theater.getAddress().getStreetNo());
            addressStatement.setInt(6,theater.getAddress().getAddressId());
            addressStatement.executeUpdate();

            statement = connection.prepareStatement(SQL_UPDATE_THEATER);
            statement.setString(1,theater.getTheaterName());
            statement.setFloat(2,theater.getRating());
            statement.setLong(3,theater.getTheaterId());
            statement.executeUpdate();
            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return null;
        }finally {
            if(statement != null){
                statement.close();
            }
            if(addressStatement != null){
                addressStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        return find(id);
    }

    @Override
    public boolean delete(Long id) throws  Exception{
        String DELETE_THEATER = "DELETE from theater_entries where theater_id = ?";
        String DELETE_ADDRESS = "DELETE from address where address_id = ?";
        Theater findTheater = find(id);
        if(findTheater == null){
            return false;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement addressStatement = null;

        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(DELETE_THEATER);
            statement.setLong(1,findTheater.getTheaterId());
            statement.executeUpdate();

            addressStatement = connection.prepareStatement(DELETE_ADDRESS);
            addressStatement.setInt(1,findTheater.getAddress().getAddressId());
            addressStatement.executeUpdate();

            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            if(statement != null){
                statement.close();
            }
            if(addressStatement != null){
                addressStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return true;
    }

}
