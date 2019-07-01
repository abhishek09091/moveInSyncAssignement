package com.example.moveinSync.moveInSync.controller;

import com.example.moveinSync.moveInSync.model.Theater;
import com.example.moveinSync.moveInSync.service.TheaterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater-entries")
public class TheaterController {

    private TheaterRepository theaterRepository;

    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @PostMapping
    public ResponseEntity<Theater> create(@RequestBody Theater theater) throws Exception {
        System.out.println(theater);
        Theater createdTheater = theaterRepository.create(theater);
        if(createdTheater == null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Theater> read(@PathVariable Long id) throws  Exception{
        Theater theater = theaterRepository.find(id);
        if (theater != null) {
            return new ResponseEntity<>(theater, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Theater> update(@PathVariable Long id, @RequestBody Theater theater) throws Exception {
        Theater theaterEntry = theaterRepository.update(id, theater);
        System.out.println(theaterEntry);
        if (theaterEntry != null) {
            return new ResponseEntity<>(theaterEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        if(theaterRepository.delete(id)){
         return new ResponseEntity<>("Deleted the Theater Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Theater Doesn't Exists in the database",HttpStatus.NOT_FOUND);
    }
}
