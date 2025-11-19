package com.aqe.dnd.dnd_catalog.features.character.dto;

import lombok.Data;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Data
public class CharacterDTO {

    @NotNull
    private String name;

    @NotNull
    private String race;

    @NotNull
    private String characterClass;

    @NotNull
    private Short level;

    private String background;
    private String alignment;

    @NotNull
    private Integer experience;

    @NotNull
    private Integer hitPoints;

    @NotNull
    private Integer armorClass;

    private Short initiativeMisc;
    private String notes;
    private String grp;

    // Relaciones
    @NotNull
    private String characterTypeCode; 
    private UUID partyId; 

    // Datos de las estadísticas
    @NotNull
    private Short strength;

    @NotNull
    private Short dexterity;

    @NotNull
    private Short constitution;

    @NotNull
    private Short intelligence;

    @NotNull
    private Short wisdom;

    @NotNull
    private Short charisma;
}
