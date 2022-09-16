package com.alkemy.disneyApi.service;

import com.alkemy.disneyApi.dto.MovieBasicDTO;
import com.alkemy.disneyApi.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    //Save new movie
    MovieDTO saveNewMovie(MovieDTO movieDTO);

    List<MovieBasicDTO> getBasicMovieInfo();

    MovieDTO getFullMovieInfo(Long id);

    MovieDTO refreshValues(Long id, MovieDTO newData);

    void deleteMovie(Long id);

    void deleteCharacter(Long movieId, Long characterId);

    void addCharacter(Long movieId, Long characterId);

    List<MovieDTO> getMoviesByFilters(String title, Long genreId, String order);
}
