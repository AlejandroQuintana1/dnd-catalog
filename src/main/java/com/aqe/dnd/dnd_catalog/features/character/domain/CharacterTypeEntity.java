package com.aqe.dnd.dnd_catalog.features.character.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "characters_types", schema = "dnd")
public class CharacterTypeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chrt_id", nullable = false)
    private Long id;

    @Column(name="code", nullable = false, unique = true)
    private String code;
    
    @Column(name="description", nullable = true)
    private String description;
}
