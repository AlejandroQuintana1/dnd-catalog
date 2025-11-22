package com.aqe.dnd.dnd_catalog.features.item.repository;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
    List<ItemEntity> findByDeletedAtIsNull();
}
