package com.aqe.dnd.dnd_catalog.features.spell.repository;

import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellSchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpellSchoolRepository extends JpaRepository<SpellSchoolEntity, Short> {
    Optional<SpellSchoolEntity> findByCode(String code);
}
