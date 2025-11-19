package com.aqe.dnd.dnd_catalog.features.character.domain;

import java.time.OffsetDateTime;
import jakarta.persistence.Table;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "stats", schema = "dnd")
public class StatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id", nullable = false)
    private Long statsId;

    @Column(nullable = false)
    private Short strength;

    @Column(nullable = false)
    private Short dexterity;

    @Column(nullable = false)
    private Short constitution;

    @Column(nullable = false)
    private Short intelligence;

    @Column(nullable = false)
    private Short wisdom;

    @Column(nullable = false)
    private Short charisma;

    // Modificadores calculados de las estadísticas
    @Column(name = "strength_mod", insertable = false, updatable = false)
    private Short strengthMod;

    @Column(name = "dexterity_mod", insertable = false, updatable = false)
    private Short dexterityMod;

    @Column(name = "constitution_mod", insertable = false, updatable = false)
    private Short constitutionMod;

    @Column(name = "intelligence_mod", insertable = false, updatable = false)
    private Short intelligenceMod;

    @Column(name = "wisdom_mod", insertable = false, updatable = false)
    private Short wisdomMod;

    @Column(name = "charisma_mod", insertable = false, updatable = false)
    private Short charismaMod;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;
}