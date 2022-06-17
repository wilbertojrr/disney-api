package com.alkemy.disneyApi.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;
}
