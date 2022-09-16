package com.alkemy.disneyApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDTO {

    private Long id;

    private String name;

    private String image;
}
