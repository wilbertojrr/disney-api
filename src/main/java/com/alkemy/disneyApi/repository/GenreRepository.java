package com.alkemy.disneyApi.repository;

import com.alkemy.disneyApi.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
