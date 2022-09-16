package com.alkemy.disneyApi.service.implement;

import com.alkemy.disneyApi.dto.GenreDTO;
import com.alkemy.disneyApi.entity.GenreEntity;
import com.alkemy.disneyApi.exception.ParamNotFound;
import com.alkemy.disneyApi.mappers.GenreMapper;
import com.alkemy.disneyApi.repository.GenreRepository;
import com.alkemy.disneyApi.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImp implements IGenreService {
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public GenreDTO saveNewGenre(GenreDTO genreDTO) {

        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDTO);
        GenreEntity genreEntitySaved = genreRepository.save(genreEntity);
        return genreMapper.genreEntity2DTO(genreEntitySaved);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        return genreMapper.genreEntityList2DTOList(genreEntities);
    }

    @Override
    public GenreDTO getGenreById(Long genreId) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(genreId);
        if(!genreEntity.isPresent()){
            throw new ParamNotFound("Genre iD not valid");
        }
        return genreMapper.genreEntity2DTO(genreEntity.get());
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO genreDTO) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if(!genreEntity.isPresent()){
            throw new ParamNotFound("Genre iD not valid");
        }
        genreMapper.genreEntityUpdate(genreEntity.get(), genreDTO);
        GenreEntity saved =  genreRepository.save(genreEntity.get());
        return genreMapper.genreEntity2DTO(saved);
    }

    @Override
    public void deleteGenre(Long id) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if(!genreEntity.isPresent()){
            throw new ParamNotFound("Genre iD not valid");
        }
        genreRepository.deleteById(id);
    }
}
