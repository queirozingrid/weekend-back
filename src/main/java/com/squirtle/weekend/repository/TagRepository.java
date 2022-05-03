package com.squirtle.weekend.repository;

import com.squirtle.weekend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag save(Tag tag);
    List<Tag> findAll();
    @Override
    Optional<Tag> findById(Long id);

}
