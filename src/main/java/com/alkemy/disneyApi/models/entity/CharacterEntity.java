package com.alkemy.disneyApi.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Float weight;

    private String history;

    @ManyToMany(mappedBy = "characters")
    private List<MovieEntity> movies = new ArrayList<>();
}
