package com.aqe.dnd.dnd_catalog.features.skill.repository;

import com.aqe.dnd.dnd_catalog.features.skill.domain.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    Optional<SkillEntity> findByCode(String code);
}
