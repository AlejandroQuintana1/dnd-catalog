package com.aqe.dnd.dnd_catalog.features.skill.domain;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "character_skill", schema = "dnd")
@IdClass(CharacterSkillEntity.CharacterSkillId.class)
public class CharacterSkillEntity {

    @Id
    @Column(name = "character_id")
    private UUID characterId;

    @Id
    @Column(name = "skill_id")
    private Long skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", insertable = false, updatable = false)
    private CharacterEntity character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private SkillEntity skill;

    @Column(nullable = false)
    private Boolean proficient;

    @Data
    public static class CharacterSkillId implements Serializable {
        private UUID characterId;
        private Long skillId;
    }
}
