package com.alkemy.disneyApi.service.implement;

import com.alkemy.disneyApi.dto.MovieBasicDTO;
import com.alkemy.disneyApi.dto.MovieDTO;
import com.alkemy.disneyApi.dto.MovieFiltersDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.entity.MovieEntity;
import com.alkemy.disneyApi.exception.ParamNotFound;
import com.alkemy.disneyApi.mappers.MovieMapper;
import com.alkemy.disneyApi.repository.MovieRepository;
import com.alkemy.disneyApi.repository.specifications.MovieSpecifications;
import com.alkemy.disneyApi.service.ICharacterService;
import com.alkemy.disneyApi.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp implements IMovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ICharacterService characterService;
    @Autowired
    private MovieSpecifications movieSpecifications;

    //Save New Movie
    public MovieDTO saveNewMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.movieDtoToEntity(movieDTO);
        MovieEntity saved = movieRepository.save(movieEntity);
        return movieMapper.movieEntityToDTO(saved,true);
    }

    @Override
    public List<MovieBasicDTO> getBasicMovieInfo() {
        List<MovieEntity> movieEntities = movieRepository.findAll();
        return movieMapper.movieEntityListToBasicDTOList(movieEntities);
    }

    @Override
    public MovieDTO getFullMovieInfo(Long id) {
        Optional <MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()){
            throw new ParamNotFound("Movie Id not found");
        }
        return movieMapper.movieEntityToDTO(movieEntity.get(),true);
    }

    @Override
    public MovieDTO refreshValues(Long id, MovieDTO newData) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()){
            throw new ParamNotFound("Movie Id not found");
        }
        movieMapper.refreshEntity(movieEntity.get(), newData);
        MovieEntity movieEntityUpdated = movieRepository.save(movieEntity.get());
        return movieMapper.movieEntityToDTO(movieEntityUpdated, false);
    }

    @Override
    public void deleteMovie(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()){
            throw new ParamNotFound("Movie Id not found");
        }
        movieRepository.deleteById(id);
    }

    @Override
    public void deleteCharacter(Long movieId, Long characterId) {
        MovieEntity movieEntity = this.getMovieEntity(movieId);
        movieEntity.getCharacters().size();
        CharacterEntity characterEntity = characterService.getCharacterEntity(characterId);
        movieEntity.deleteCharacter(characterEntity);
        movieRepository.save(movieEntity);
    }

    @Override
    public void addCharacter(Long movieId, Long characterId) {
        MovieEntity movieEntity = this.getMovieEntity(movieId);
        movieEntity.getCharacters().size();
        CharacterEntity characterEntity = characterService.getCharacterEntity(characterId);
        movieEntity.addCharacter(characterEntity);
        movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieDTO> getMoviesByFilters(String title, Long genreId, String order) {
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(title,genreId,order);
        List<MovieEntity> movieEntities = movieRepository.findAll(movieSpecifications.getByFilters(movieFiltersDTO));
        return movieMapper.movieEntityListToDTOList(movieEntities,true);
    }

    public MovieEntity getMovieEntity (Long id){
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()){
            throw new ParamNotFound("Movie Id not found");
        }
        return movieEntity.get();
    }
}
