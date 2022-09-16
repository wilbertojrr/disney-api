package com.alkemy.disneyApi.controllers;

import com.alkemy.disneyApi.dto.MovieBasicDTO;
import com.alkemy.disneyApi.dto.MovieDTO;
import com.alkemy.disneyApi.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    //Create Movie
    @PostMapping
    public ResponseEntity<MovieDTO> createNewMovie (@RequestBody MovieDTO movieDTO) {
        MovieDTO savedMovie = movieService.saveNewMovie(movieDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

    }
    //Get basic movie info
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicInfo (){
        List <MovieBasicDTO> movieBasicList = movieService.getBasicMovieInfo();
        return ResponseEntity.ok().body(movieBasicList);
    }
    //Get full movie info by ID
    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getFullInfo (@PathVariable Long id){
        MovieDTO movieDTO = movieService.getFullMovieInfo(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    //Update movie
    @PutMapping("{id}")
    public ResponseEntity<MovieDTO> updateMovie (@PathVariable Long id, @RequestBody MovieDTO newData){
        MovieDTO movieUpdateDto = movieService.refreshValues(id, newData);
        return ResponseEntity.ok().body(movieUpdateDto);
    }
    //delete movie
    @DeleteMapping("{id}")
    public  ResponseEntity<Void> deleteMovie (@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Delete character from movie
    @DeleteMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<Void> removeCharacter (@PathVariable Long movieId, @PathVariable Long characterId){
        movieService.deleteCharacter(movieId,characterId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Add characters to movie
    @PostMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<Void> addCharacter (@PathVariable Long movieId, @PathVariable Long characterId){
        movieService.addCharacter(movieId,characterId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Filters
    @GetMapping("filters")
    public ResponseEntity<List<MovieDTO>> movieFilters (
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> filters = movieService.getMoviesByFilters (title,genreId,order);
        return ResponseEntity.ok(filters);
    }

}
