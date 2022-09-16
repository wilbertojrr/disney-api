package com.alkemy.disneyApi.service.implement;

import com.alkemy.disneyApi.dto.CharacterBasicDTO;
import com.alkemy.disneyApi.dto.CharacterDTO;
import com.alkemy.disneyApi.dto.CharacterFiltersDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.exception.ParamNotFound;
import com.alkemy.disneyApi.mappers.CharacterMapper;
import com.alkemy.disneyApi.repository.CharacterRepository;
import com.alkemy.disneyApi.repository.specifications.CharacterSpecifications;
import com.alkemy.disneyApi.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterSpecifications characterSpecifications;

    public CharacterDTO saveCharacter(CharacterDTO dto) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity characterEntitySaved = characterRepository.save(characterEntity);
        return characterMapper.characterEntity2DTO(characterEntitySaved,false);

    }

    @Override
    public List<CharacterBasicDTO> getAllBasicData() {
        List<CharacterEntity> characterEntitiesBasic = characterRepository.findAll();
        return characterMapper.characterEntityToBasicDTOList(characterEntitiesBasic);
    }

    @Override
    public CharacterDTO getFullCharacterInfo(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("Character Id not found");
        }
        return characterMapper.characterEntity2DTO(character.get(),true);
    }

    @Override
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Optional <CharacterEntity> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("Character Id not found");
        }
        characterMapper.characterUpdateValues(character.get(), characterDTO);
        CharacterEntity characterSaved = characterRepository.save(character.get());
        return characterMapper.characterEntity2DTO(characterSaved, false);
    }

    @Override
    public void deleteCharacter(Long id) {
        Optional <CharacterEntity> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("Character Id not found");
        }
        characterRepository.deleteById(id);
    }

    @Override
    public CharacterEntity getCharacterEntity(Long id) {
        Optional <CharacterEntity> character = characterRepository.findById(id);
        if(!character.isPresent()){
            throw new ParamNotFound("Character Id not found");
        }
        return character.get();
    }

    @Override
    public List<CharacterDTO> getCharacterByFilter(String name, Integer age, Set<Long> movieId) {
        CharacterFiltersDTO characterFiltersDTO = new CharacterFiltersDTO (name,age,movieId);
        List<CharacterEntity> characterEntities = characterRepository.findAll(characterSpecifications.getByFilters(characterFiltersDTO));
        return characterMapper.characterEntityList2DTOList(characterEntities,true);
    }


}
