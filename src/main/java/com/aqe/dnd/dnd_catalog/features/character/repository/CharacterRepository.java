package com.aqe.dnd.dnd_catalog.features.character.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, UUID> {
    List<CharacterEntity> findByDeletedAtIsNull();
}
