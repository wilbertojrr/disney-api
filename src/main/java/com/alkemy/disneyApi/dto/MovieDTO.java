package com.alkemy.disneyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private LocalDate creatDate;
    private Integer rating;
    private Long genreId;
    private List<CharacterDTO> characters = new ArrayList<>();
}
