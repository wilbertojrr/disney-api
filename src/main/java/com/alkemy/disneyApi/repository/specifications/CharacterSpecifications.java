package com.alkemy.disneyApi.repository.specifications;

import com.alkemy.disneyApi.dto.CharacterFiltersDTO;
import com.alkemy.disneyApi.entity.CharacterEntity;
import com.alkemy.disneyApi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecifications {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO characterFiltersDTO) {

        return (root,query,criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();

            // name
            if(StringUtils.hasLength(characterFiltersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            //Age
            if (characterFiltersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), characterFiltersDTO.getAge())
                );
            }
            //movies

            if(!CollectionUtils.isEmpty(characterFiltersDTO.getMovieId())) {
                Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(characterFiltersDTO.getMovieId()));


            }
            query.distinct(true);

            //Order
            query.orderBy(criteriaBuilder.asc(root.get("name")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
