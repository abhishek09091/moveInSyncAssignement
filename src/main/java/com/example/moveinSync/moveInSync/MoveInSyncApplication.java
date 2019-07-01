package com.example.moveinSync.moveInSync;

import com.example.moveinSync.moveInSync.service.MovieRepository;
import com.example.moveinSync.moveInSync.service.SearchRepository;
import com.example.moveinSync.moveInSync.service.TheaterRepository;
import com.example.moveinSync.moveInSync.serviceImpl.JDBCMovieRepository;
import com.example.moveinSync.moveInSync.serviceImpl.JDBCSearchRepository;
import com.example.moveinSync.moveInSync.serviceImpl.JDBCTheaterRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.sql.DataSource;

@SpringBootApplication
public class MoveInSyncApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoveInSyncApplication.class, args);
	}

    @Bean
    TheaterRepository theaterEntryRepository(DataSource dataSource) {
	    return new JDBCTheaterRepository(dataSource);
    }


    @Bean
    MovieRepository movieEntryRepository(DataSource dataSource) {
        return new JDBCMovieRepository(dataSource);
    }

    @Bean
    SearchRepository searchRepositoryRepository(DataSource dataSource) {
        return new JDBCSearchRepository(dataSource);
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JavaTimeModule())
                .build();
    }

}
