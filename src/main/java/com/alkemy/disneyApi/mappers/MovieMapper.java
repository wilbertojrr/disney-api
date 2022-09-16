package com.alkemy.disneyApi.mappers;

import com.alkemy.disneyApi.dto.CharacterDTO;
import com.alkemy.disneyApi.dto.MovieBasicDTO;
import com.alkemy.disneyApi.dto.MovieDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {
    @Autowired
    private CharacterMapper characterMapper;


    // MovieDTO to MovieEntity
    public MovieEntity movieDtoToEntity(MovieDTO movieDTO) {

        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setGenreId(movieDTO.getGenreId());
        movieEntity.setCreatDate(movieDTO.getCreatDate());
        movieEntity.setRating(movieDTO.getRating());

        List<CharacterEntity> characterEntityList = characterMapper.characterDTOList2EntityList(movieDTO.getCharacters());
        movieEntity.setCharacters(characterEntityList);
        return movieEntity;
    }

    // MovieEntity to MovieDTO

    public MovieDTO movieEntityToDTO(MovieEntity movieEntity, boolean loadCharacters) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setCreatDate(movieEntity.getCreatDate());
        movieDTO.setGenreId(movieEntity.getGenreId());
        movieDTO.setRating(movieEntity.getRating());
        movieDTO.setId(movieEntity.getId());

        if (loadCharacters) {
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntityList2DTOList(movieEntity.getCharacters(), false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
    }

    // MovieEntityList to MovieDTOList

    public List<MovieDTO> movieEntityListToDTOList(List<MovieEntity> movieEntities, boolean loadCharacters) {
        List<MovieDTO> movieDTOS = new ArrayList<>();

        for (MovieEntity entity : movieEntities) {
            movieDTOS.add(this.movieEntityToDTO(entity, loadCharacters));
        }
        return movieDTOS;
    }

    public List<MovieEntity> movieDTOListToEntityList(List<MovieDTO> movieDTOS) {
        List<MovieEntity> movieEntities = new ArrayList<>();

        for (MovieDTO dto : movieDTOS) {
            movieEntities.add(this.movieDtoToEntity(dto));
        }
        return movieEntities;
    }


    public List<MovieBasicDTO> movieEntityListToBasicDTOList(List<MovieEntity> movieEntities) {
        List<MovieBasicDTO> basicDTOS = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (MovieEntity entity : movieEntities) {
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setTitle(entity.getTitle());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }

    public void refreshEntity(MovieEntity movieEntity, MovieDTO newData) {
        movieEntity.setImage(newData.getImage());
        movieEntity.setRating(newData.getRating());
        movieEntity.setGenreId(newData.getGenreId());
        movieEntity.setTitle(newData.getTitle());
        movieEntity.setCreatDate(newData.getCreatDate());
    }
}
