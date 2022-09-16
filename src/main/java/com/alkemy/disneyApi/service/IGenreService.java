package com.alkemy.disneyApi.service;

import com.alkemy.disneyApi.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    //save new genre
    GenreDTO saveNewGenre(GenreDTO genreDTO);

    //Get all genres
    List<GenreDTO> getAllGenres();

    //Get genre by Id
    GenreDTO getGenreById(Long genreId);

    //Update Genre
    GenreDTO updateGenre(Long id, GenreDTO genreDTO);

    void deleteGenre(Long id);
}
