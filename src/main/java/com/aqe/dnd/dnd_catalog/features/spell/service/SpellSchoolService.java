package com.aqe.dnd.dnd_catalog.features.spell.service;

import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellSchoolEntity;
import com.aqe.dnd.dnd_catalog.features.spell.repository.SpellSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SpellSchoolService {

    private final SpellSchoolRepository spellSchoolRepository;

    public SpellSchoolEntity getByCode(String code) {
        return spellSchoolRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Spell School not found with code: " + code));
    }
}
