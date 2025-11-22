package com.aqe.dnd.dnd_catalog.features.character.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PartyDTO {
    private UUID id;
    private String name;
    private UUID campaignId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    @JsonIgnore
    private OffsetDateTime deletedAt;
}
