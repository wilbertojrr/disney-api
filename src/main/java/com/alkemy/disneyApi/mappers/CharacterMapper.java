package com.alkemy.disneyApi.mappers;

import com.alkemy.disneyApi.dto.CharacterBasicDTO;
import com.alkemy.disneyApi.dto.CharacterDTO;
import com.alkemy.disneyApi.dto.MovieDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    //CharacterDTO to CharacterEntity

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setName(dto.getName());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setAge(dto.getAge());
        List<MovieEntity> movieEntityList = movieMapper.movieDTOListToEntityList(dto.getMovieDTO());
        characterEntity.setMovies(movieEntityList);
        return characterEntity;
    }

    // CharacterEntity to CharacterDTO
    public CharacterDTO characterEntity2DTO(CharacterEntity characterEntity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(characterEntity.getId());
        characterDTO.setHistory(characterEntity.getHistory());
        characterDTO.setAge(characterEntity.getAge());
        characterDTO.setName(characterEntity.getName());
        characterDTO.setImage(characterEntity.getImage());
        characterDTO.setWeight(characterEntity.getWeight());

        if (loadMovies) {
            List<MovieDTO> movieDTOList = movieMapper.movieEntityListToDTOList(characterEntity.getMovies(), false);
            characterDTO.setMovieDTO(movieDTOList);
        }

        return characterDTO;
    }

    //CharacterDTO list to CharacterEntityList
    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> characterDTOS) {

        List<CharacterEntity> characterEntities = new ArrayList<>();

        for (CharacterDTO dto : characterDTOS) {
            characterEntities.add(this.characterDTO2Entity(dto));
        }
        return characterEntities;
    }

    //CharacterEntity list to CharacterDTO List

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characterEntities, boolean loadMovies) {

        List<CharacterDTO> characterDTOS = new ArrayList<>();

        for (CharacterEntity entity : characterEntities) {
            characterDTOS.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return characterDTOS;
    }

    public List<CharacterBasicDTO> characterEntityToBasicDTOList(List<CharacterEntity> characterEntities) {
        List<CharacterBasicDTO> characterBasicDTOS = new ArrayList<>();
        CharacterBasicDTO characterBasicDTO;
        for (CharacterEntity entity : characterEntities) {
            characterBasicDTO = new CharacterBasicDTO();
            characterBasicDTO.setId(entity.getId());
            characterBasicDTO.setImage(entity.getImage());
            characterBasicDTO.setName(entity.getName());
            characterBasicDTOS.add(characterBasicDTO);
        }
        return characterBasicDTOS;
    }

    public void characterUpdateValues(CharacterEntity characterEntity, CharacterDTO characterDTO) {
        characterEntity.setAge(characterDTO.getAge());
        characterEntity.setImage(characterDTO.getImage());
        characterEntity.setName(characterDTO.getName());
        characterEntity.setWeight(characterDTO.getWeight());
        characterEntity.setHistory(characterDTO.getHistory());
    }
}
