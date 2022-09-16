
package com.alkemy.disneyApi.controllers;

import com.alkemy.disneyApi.dto.GenreDTO;
import com.alkemy.disneyApi.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private IGenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> CreatNewGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO genreSaved = genreService.saveNewGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }

    //Get all genders
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genreDTOS = genreService.getAllGenres();
        return ResponseEntity.ok().body(genreDTOS);
    }

    //Get gender by ID
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGernreById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.getGenreById(id);
        return ResponseEntity.ok().body(genreDTO);
    }

    //Update genre
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        GenreDTO updateGenre = genreService.updateGenre(id, genreDTO);
        return ResponseEntity.ok().body(updateGenre);
    }

    //Delete genre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}