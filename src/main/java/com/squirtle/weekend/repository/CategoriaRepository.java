package com.squirtle.weekend.repository;

import com.squirtle.weekend.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
}
