package com.aqe.dnd.dnd_catalog.features.spell.repository;

import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpellRepository extends JpaRepository<SpellEntity, UUID> {
    List<SpellEntity> findByDeletedAtIsNull();
}
