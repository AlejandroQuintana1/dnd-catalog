package com.aqe.dnd.dnd_catalog.features.item.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item_slot", schema = "dnd")
public class ItemSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id", nullable = false)
    private Short id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;
}
