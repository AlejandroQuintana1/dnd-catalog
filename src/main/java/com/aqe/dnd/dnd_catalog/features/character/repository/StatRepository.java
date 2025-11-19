package com.aqe.dnd.dnd_catalog.features.character.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aqe.dnd.dnd_catalog.features.character.domain.StatEntity;

public interface StatRepository extends JpaRepository<StatEntity, Long> {

    Optional<StatEntity> findByCharacterId(UUID characterId);
    
}
