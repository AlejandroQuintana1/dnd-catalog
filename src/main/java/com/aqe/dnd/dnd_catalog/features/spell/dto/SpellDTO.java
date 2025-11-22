package com.aqe.dnd.dnd_catalog.features.spell.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SpellDTO {
    private UUID id;
    private String name;
    private Short level;
    private String schoolCode;
    private String castingTime;
    private String range;
    private String components;
    private String duration;
    private String description;
}
