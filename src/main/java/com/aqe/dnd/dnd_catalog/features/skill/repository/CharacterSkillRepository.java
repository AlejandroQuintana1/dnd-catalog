package com.aqe.dnd.dnd_catalog.features.skill.repository;

import com.aqe.dnd.dnd_catalog.features.skill.domain.CharacterSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CharacterSkillRepository
        extends JpaRepository<CharacterSkillEntity, CharacterSkillEntity.CharacterSkillId> {
    List<CharacterSkillEntity> findByCharacterId(UUID characterId);
}
