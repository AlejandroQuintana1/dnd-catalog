package com.aqe.dnd.dnd_catalog.features.character.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterTypeEntity;

public interface CharacterTypeRepository extends JpaRepository<CharacterTypeEntity, Long> {
    Optional<CharacterTypeEntity> findByCode(String code);
}
