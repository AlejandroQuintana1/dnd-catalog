package com.aqe.dnd.dnd_catalog.features.skill.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill", schema = "dnd")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String ability;
}
