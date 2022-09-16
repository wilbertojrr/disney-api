package com.alkemy.disneyApi.controllers;

import com.alkemy.disneyApi.dto.CharacterBasicDTO;
import com.alkemy.disneyApi.dto.CharacterDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.service.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private ICharacterService characterService;

    //Create new character
    @PostMapping
    public ResponseEntity <CharacterDTO> createNewCharacter (@RequestBody CharacterDTO characterDTO){

        CharacterDTO savedCharacter = characterService.saveCharacter(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    // Get basic info (show only name and image)

    @GetMapping
    public ResponseEntity <List<CharacterBasicDTO>> getAllBasicCharacterData (){
        List<CharacterBasicDTO> characterBasicDTOS = characterService.getAllBasicData();
        return ResponseEntity.ok().body(characterBasicDTOS);
    }

    //Get full info (search by ID)
    @GetMapping("{id}")
    public ResponseEntity<CharacterDTO> getFullInfoById (@PathVariable Long id){
        CharacterDTO characterDTO = characterService.getFullCharacterInfo(id);
        return ResponseEntity.ok().body(characterDTO);
    }

    //Update character
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter (@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        CharacterDTO updatedCharacter = characterService.updateCharacter(id,characterDTO);
        return ResponseEntity.ok().body(updatedCharacter);

    }
    //Delete character
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCharacter (@PathVariable Long id){
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //Filters
    @GetMapping("filters")
    public ResponseEntity<List<CharacterDTO>> characterFilters (
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Set <Long> movieId
            ){
        List<CharacterDTO> characterDTOS = characterService.getCharacterByFilter(name,age,movieId);
        return ResponseEntity.ok().body(characterDTOS);
    }

}
