package com.example.moveinSync.moveInSync.serviceImpl;

import com.example.moveinSync.moveInSync.model.Movie;
import com.example.moveinSync.moveInSync.model.MovieType;
import com.example.moveinSync.moveInSync.service.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JDBCMovieRepository implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    public JDBCMovieRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Movie create(Movie movie) throws Exception {

        if(movie == null){
            return  null;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String CREATE_MOVIE = "insert into movies (movie_id , movie_name , movie_type) VALUES (?, ? ,?)";
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_MOVIE);
            preparedStatement.setInt(1,movie.getMovieId());
            preparedStatement.setString(2,movie.getMovieName());
            preparedStatement.setString(3,movie.getMovieType().toString());
            System.out.println(preparedStatement.executeUpdate());
            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return null;
        }finally {

            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return find(movie.getMovieId());
    }

    @Override
    public Movie find(Integer id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Movie movie = null;
        String CREATE_MOVIE = "select * from movies where movie_id = ? ";
        try{
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_MOVIE);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                movie = new Movie();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setMovieName(rs.getString("movie_name"));
                movie.setMovieType((MovieType.valueOf( rs.getString("movie_type"))));
            }
            connection.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            connection.rollback();
            return null;
        }finally {

            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return movie;
    }

    @Override
    public List<Movie> list() {
        return null;
    }

    @Override
    public Movie update(Long id, Movie movie) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
