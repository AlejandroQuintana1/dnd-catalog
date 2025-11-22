package com.aqe.dnd.dnd_catalog.features.skill.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CharacterSkillDTO {
    private UUID characterId;
    private Long skillId;
    private String skillCode;
    private Boolean proficient;
}
