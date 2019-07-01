package com.example.moveinSync.moveInSync.serviceImpl;

import com.example.moveinSync.moveInSync.model.Address;
import com.example.moveinSync.moveInSync.model.Theater;
import com.example.moveinSync.moveInSync.service.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCSearchRepository implements SearchRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    public JDBCSearchRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Theater> getListTheater(String cityName) throws Exception{
        if(cityName == null || "".equals(cityName)){
            return new ArrayList<>();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Theater> theaterList = new ArrayList<>();
        String GET_THEATERS = "select * from address , theater_entries where address.address_id = theater_entries.address_id and city = ? ";
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(GET_THEATERS);
            preparedStatement.setString(1,cityName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Theater theater = new Theater();
                theater.setTheaterId(resultSet.getLong("theater_id"));
                theater.setRating(resultSet.getFloat("rating"));
                Address address = new Address();
                address.setAddressId(resultSet.getInt("address_id"));
                address.setCity(resultSet.getString("city"));
                address.setPinCode(resultSet.getString("pin_code"));
                address.setState(resultSet.getString("state"));
                address.setLandmark(resultSet.getString("landmark"));
                address.setStreetNo(resultSet.getString("street_no"));
                theater.setAddress(address);
            }
            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return theaterList;
        }finally {

            if (connection != null){
                connection.close();
            }
        }
        return theaterList;
    }
}
