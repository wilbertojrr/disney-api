package com.alkemy.disneyApi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;

    private boolean deleted = Boolean.FALSE;
}
