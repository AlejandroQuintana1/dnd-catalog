package com.aqe.dnd.dnd_catalog.features.item.service;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemEntity;
import com.aqe.dnd.dnd_catalog.features.item.domain.ItemSlotEntity;
import com.aqe.dnd.dnd_catalog.features.item.dto.ItemDTO;
import com.aqe.dnd.dnd_catalog.features.item.repository.ItemRepository;
import com.aqe.dnd.dnd_catalog.features.skill.domain.ItemSkillBonusEntity;
import com.aqe.dnd.dnd_catalog.features.skill.domain.SkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.dto.ItemSkillBonusDTO;
import com.aqe.dnd.dnd_catalog.features.skill.repository.ItemSkillBonusRepository;
import com.aqe.dnd.dnd_catalog.features.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemSlotService itemSlotService;
    private final ItemSkillBonusRepository itemSkillBonusRepository;
    private final SkillService skillService;

    @Transactional
    public ItemEntity create(ItemDTO itemDTO) {
        ItemEntity item = new ItemEntity();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setWeight(itemDTO.getWeight());
        item.setAttunementRequired(itemDTO.getAttunementRequired());
        item.setLore(itemDTO.getLore());

        if (itemDTO.getSlotCode() != null) {
            ItemSlotEntity slot = itemSlotService.getByCode(itemDTO.getSlotCode());
            item.setSlot(slot);
        }

        item.setCreatedAt(OffsetDateTime.now());
        item.setUpdatedAt(OffsetDateTime.now());

        ItemEntity savedItem = itemRepository.save(item);

        if (itemDTO.getItemSkillBonuses() != null) {
            saveItemSkillBonuses(savedItem, itemDTO.getItemSkillBonuses());
        }

        return savedItem;
    }

    public ItemEntity get(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found"));
    }

    @Transactional
    public ItemEntity update(UUID id, ItemDTO itemDTO) {
        ItemEntity item = get(id);

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setWeight(itemDTO.getWeight());
        item.setAttunementRequired(itemDTO.getAttunementRequired());
        item.setLore(itemDTO.getLore());

        if (itemDTO.getSlotCode() != null) {
            ItemSlotEntity slot = itemSlotService.getByCode(itemDTO.getSlotCode());
            item.setSlot(slot);
        }

        item.setUpdatedAt(OffsetDateTime.now());

        ItemEntity savedItem = itemRepository.save(item);

        if (itemDTO.getItemSkillBonuses() != null) {
            itemSkillBonusRepository.deleteByItemId(id);
            saveItemSkillBonuses(savedItem, itemDTO.getItemSkillBonuses());
        }

        return savedItem;
    }

    private void saveItemSkillBonuses(ItemEntity item, List<ItemSkillBonusDTO> bonusDTOs) {
        for (ItemSkillBonusDTO bonusDTO : bonusDTOs) {
            SkillEntity skill;
            if (bonusDTO.getSkillId() != null) {
                skill = skillService.getById(bonusDTO.getSkillId());
            } else {
                skill = skillService.getByCode(bonusDTO.getSkillCode());
            }

            ItemSkillBonusEntity bonusEntity = new ItemSkillBonusEntity();
            bonusEntity.setItemId(item.getId());
            bonusEntity.setSkillId(skill.getId());
            bonusEntity.setBonus(bonusDTO.getBonus());

            itemSkillBonusRepository.save(bonusEntity);
        }
    }

    public void delete(UUID id) {
        ItemEntity item = get(id);
        item.setDeletedAt(OffsetDateTime.now());
        itemRepository.save(item);
    }

    public List<ItemEntity> getAll() {
        return itemRepository.findByDeletedAtIsNull();
    }
}
