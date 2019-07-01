package com.example.moveinSync.moveInSync.controller;


import com.example.moveinSync.moveInSync.service.SearchRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom-search")
public class CustomSearchController {

    private SearchRepository searchRepository;

    public CustomSearchController( SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
}
