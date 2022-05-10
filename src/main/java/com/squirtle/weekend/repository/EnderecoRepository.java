package com.squirtle.weekend.repository;

import com.squirtle.weekend.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco save(Endereco endereco);

    List<Endereco> findAll();

    @Override
    Optional<Endereco> findById(Long id);

    Optional<Endereco> findByCep(String cep);
}
