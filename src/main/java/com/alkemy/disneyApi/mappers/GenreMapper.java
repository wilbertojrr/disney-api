package com.alkemy.disneyApi.mappers;

import com.alkemy.disneyApi.dto.GenreDTO;
import com.alkemy.disneyApi.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    //Refresh genre entity
    public void genreEntityUpdate (GenreEntity entity, GenreDTO dto){
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
    }

    // GenreDTO to GenreEntity
    public GenreEntity genreDTO2Entity(GenreDTO dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());

        return genreEntity;
    }

    // GenreEntity to GenreDTO
    public GenreDTO genreEntity2DTO (GenreEntity genreEntity){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genreEntity.getId());
        genreDTO.setImage(genreEntity.getImage());
        genreDTO.setName(genreEntity.getName());

        return genreDTO;
    }

    //GenreEntity List to DTO List
    public List<GenreDTO> genreEntityList2DTOList (List<GenreEntity> genreEntities){
        List<GenreDTO> genreDtos = new ArrayList<>();

        for(GenreEntity genreEntity : genreEntities){
            genreDtos.add(this.genreEntity2DTO(genreEntity));
        }
        return genreDtos;
    }
}
