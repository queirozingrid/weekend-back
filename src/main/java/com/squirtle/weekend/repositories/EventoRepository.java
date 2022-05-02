package com.squirtle.weekend.repositories;


import com.squirtle.weekend.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Evento save(Evento evento);

    @Override
    List<Evento> findAll();
}
