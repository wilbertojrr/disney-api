package com.alkemy.disneyApi.repository.specifications;

import com.alkemy.disneyApi.dto.MovieFiltersDTO;
import com.alkemy.disneyApi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecifications {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO movieFiltersDTO) {
        return (root, query, criteriaBuilder) ->{

            List<Predicate> predicates = new ArrayList<>();

            //filter by title
            if(StringUtils.hasLength(movieFiltersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFiltersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }


            //filter by genreId
            if(movieFiltersDTO.getGenreId() != null){
                predicates.add(
                        criteriaBuilder.equal(root.get("genreId"),movieFiltersDTO.getGenreId())
                );
            }
            query.distinct(true);

            // filter by order
            String orderField = "title";
            query.orderBy(
                    movieFiltersDTO.asc() ? criteriaBuilder.asc(root.get(orderField)):
                            criteriaBuilder.desc(root.get(orderField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
