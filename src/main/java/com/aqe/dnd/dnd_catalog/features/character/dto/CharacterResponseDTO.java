package com.aqe.dnd.dnd_catalog.features.character.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CharacterResponseDTO {

    private UUID id;

    private String name;

    private String race;

    private String characterClass;

    private Short level;

    private String background;

    private String alignment;

    private Integer experience;

    private Integer hitPoints;

    private Integer armorClass;

    private Short initiativeMisc;

    private String notes;

    private String grp;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @JsonIgnore
    private OffsetDateTime deletedAt;
}
