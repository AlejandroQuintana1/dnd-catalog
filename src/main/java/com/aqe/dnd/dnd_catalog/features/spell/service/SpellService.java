package com.aqe.dnd.dnd_catalog.features.spell.service;

import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellEntity;
import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellSchoolEntity;
import com.aqe.dnd.dnd_catalog.features.spell.dto.SpellDTO;
import com.aqe.dnd.dnd_catalog.features.spell.repository.SpellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpellService {

    private final SpellRepository spellRepository;
    private final SpellSchoolService spellSchoolService;

    public SpellEntity create(SpellDTO spellDTO) {
        SpellEntity spell = new SpellEntity();
        spell.setName(spellDTO.getName());
        spell.setLevel(spellDTO.getLevel());
        spell.setCastingTime(spellDTO.getCastingTime());
        spell.setRange(spellDTO.getRange());
        spell.setComponents(spellDTO.getComponents());
        spell.setDuration(spellDTO.getDuration());
        spell.setDescription(spellDTO.getDescription());

        if (spellDTO.getSchoolCode() != null) {
            SpellSchoolEntity school = spellSchoolService.getByCode(spellDTO.getSchoolCode());
            spell.setSchool(school);
        }

        spell.setCreatedAt(OffsetDateTime.now());
        spell.setUpdatedAt(OffsetDateTime.now());

        return spellRepository.save(spell);
    }

    public SpellEntity get(UUID id) {
        return spellRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Spell not found"));
    }

    public SpellEntity update(UUID id, SpellDTO spellDTO) {
        SpellEntity spell = get(id);

        spell.setName(spellDTO.getName());
        spell.setLevel(spellDTO.getLevel());
        spell.setCastingTime(spellDTO.getCastingTime());
        spell.setRange(spellDTO.getRange());
        spell.setComponents(spellDTO.getComponents());
        spell.setDuration(spellDTO.getDuration());
        spell.setDescription(spellDTO.getDescription());

        if (spellDTO.getSchoolCode() != null) {
            SpellSchoolEntity school = spellSchoolService.getByCode(spellDTO.getSchoolCode());
            spell.setSchool(school);
        }

        spell.setUpdatedAt(OffsetDateTime.now());

        return spellRepository.save(spell);
    }

    public void delete(UUID id) {
        SpellEntity spell = get(id);
        spell.setDeletedAt(OffsetDateTime.now());
        spellRepository.save(spell);
    }

    public List<SpellEntity> getAll() {
        return spellRepository.findByDeletedAtIsNull();
    }
}
