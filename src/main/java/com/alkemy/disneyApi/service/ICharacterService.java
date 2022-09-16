package com.alkemy.disneyApi.service;

import com.alkemy.disneyApi.dto.CharacterBasicDTO;
import com.alkemy.disneyApi.dto.CharacterDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface ICharacterService {

    // save new character

    CharacterDTO saveCharacter (CharacterDTO dto);

    List<CharacterBasicDTO> getAllBasicData();

    CharacterDTO getFullCharacterInfo(Long id);

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    void deleteCharacter(Long id);

    CharacterEntity getCharacterEntity (Long id);

    List<CharacterDTO> getCharacterByFilter(String name, Integer age, Set<Long> movieId);
}
