package com.alkemy.disneyApi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movies SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creatDate;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "character_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<CharacterEntity> characters = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    public void addCharacter(CharacterEntity entity) {
        characters.add(entity);
    }

    public void deleteCharacter(CharacterEntity entity) {
        characters.remove(entity);
    }

}
