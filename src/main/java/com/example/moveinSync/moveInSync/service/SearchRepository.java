package com.example.moveinSync.moveInSync.service;

import com.example.moveinSync.moveInSync.model.Theater;

import java.util.List;

public interface SearchRepository {

    List<Theater> getListTheater(String cityName) throws Exception;
}
