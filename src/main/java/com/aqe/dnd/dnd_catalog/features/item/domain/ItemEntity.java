package com.aqe.dnd.dnd_catalog.features.item.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "item", schema = "dnd")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", nullable = false)
    private ItemSlotEntity slot;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(name = "attunement_required", nullable = false)
    private Boolean attunementRequired;

    @Column(nullable = true)
    private String lore;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
}
