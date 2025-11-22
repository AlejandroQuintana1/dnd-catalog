package com.aqe.dnd.dnd_catalog.features.item.dto;

import com.aqe.dnd.dnd_catalog.features.skill.dto.ItemSkillBonusDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class ItemDTO {
    private UUID id;
    private String name;
    private String slotCode;
    private String description;
    private BigDecimal weight;
    private Boolean attunementRequired;
    private String lore;
    private List<ItemSkillBonusDTO> itemSkillBonuses;
}
