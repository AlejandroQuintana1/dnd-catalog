package com.aqe.dnd.dnd_catalog.features.skill.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemSkillBonusDTO {
    private UUID itemId;
    private Long skillId;
    private String skillCode;
    private Short bonus;
}
