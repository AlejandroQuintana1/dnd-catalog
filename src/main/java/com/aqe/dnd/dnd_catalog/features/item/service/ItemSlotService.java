package com.aqe.dnd.dnd_catalog.features.item.service;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemSlotEntity;
import com.aqe.dnd.dnd_catalog.features.item.repository.ItemSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemSlotService {

    private final ItemSlotRepository itemSlotRepository;

    public ItemSlotEntity getByCode(String code) {
        return itemSlotRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Item Slot not found with code: " + code));
    }
}
