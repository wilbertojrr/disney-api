package com.alkemy.disneyApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFiltersDTO {
    private String title;
    private Long genreId;
    private String order;

    public boolean asc() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean desc() {
        return order.compareToIgnoreCase("DESC") == 0;
    }

}
