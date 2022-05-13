package com.squirtle.weekend.repository;

import com.squirtle.weekend.models.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    Estabelecimento save(Estabelecimento estabelecimento);

    @Override
    List<Estabelecimento> findAll();

    @Override
    Optional<Estabelecimento> findById(Long id);

}
