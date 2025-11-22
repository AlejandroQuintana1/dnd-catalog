package com.aqe.dnd.dnd_catalog.features.skill.domain;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "item_skill_bonus", schema = "dnd")
@IdClass(ItemSkillBonusEntity.ItemSkillBonusId.class)
public class ItemSkillBonusEntity {

    @Id
    @Column(name = "item_id")
    private UUID itemId;

    @Id
    @Column(name = "skill_id")
    private Long skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private SkillEntity skill;

    @Column(nullable = false)
    private Short bonus;

    @Data
    public static class ItemSkillBonusId implements Serializable {
        private UUID itemId;
        private Long skillId;
    }
}
