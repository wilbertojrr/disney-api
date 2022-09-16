package com.alkemy.disneyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Set<Long> movieId;
}
