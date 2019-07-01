package com.example.moveinSync.moveInSync.service;

import com.example.moveinSync.moveInSync.model.Theater;

import java.util.List;

public interface TheaterRepository {

    Theater create(Theater theater) throws Exception;
    Theater find(Long id) throws  Exception;
    List<Theater> list();
    Theater update(Long id, Theater theater) throws Exception;
    boolean delete(Long id) throws Exception;
}
