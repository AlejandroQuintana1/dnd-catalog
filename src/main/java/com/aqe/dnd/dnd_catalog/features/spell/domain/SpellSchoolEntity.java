package com.aqe.dnd.dnd_catalog.features.spell.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "spell_school", schema = "dnd")
public class SpellSchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id", nullable = false)
    private Short id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, unique = true)
    private String name;
}
