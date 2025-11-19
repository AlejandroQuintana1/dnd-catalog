package com.aqe.dnd.dnd_catalog.features.character.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;

public interface PartyRepository extends JpaRepository<PartyEntity, UUID> {
    Optional<PartyEntity> findByName(String name);
}
