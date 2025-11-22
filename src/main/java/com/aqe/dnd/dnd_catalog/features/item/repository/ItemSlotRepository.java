package com.aqe.dnd.dnd_catalog.features.item.repository;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemSlotRepository extends JpaRepository<ItemSlotEntity, Short> {
    Optional<ItemSlotEntity> findByCode(String code);
}
