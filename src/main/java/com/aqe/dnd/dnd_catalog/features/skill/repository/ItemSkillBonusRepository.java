package com.aqe.dnd.dnd_catalog.features.skill.repository;

import com.aqe.dnd.dnd_catalog.features.skill.domain.ItemSkillBonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemSkillBonusRepository
        extends JpaRepository<ItemSkillBonusEntity, ItemSkillBonusEntity.ItemSkillBonusId> {
    List<ItemSkillBonusEntity> findByItemId(UUID itemId);

    void deleteByItemId(UUID itemId);
}
