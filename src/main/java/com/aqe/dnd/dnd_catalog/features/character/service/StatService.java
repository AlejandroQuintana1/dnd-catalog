package com.aqe.dnd.dnd_catalog.features.character.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.StatEntity;
import com.aqe.dnd.dnd_catalog.features.character.repository.CharacterRepository;
import com.aqe.dnd.dnd_catalog.features.character.repository.StatRepository;

@Service
public class StatService {

    @Autowired
    private StatRepository statRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public StatEntity updateStats(UUID characterId, StatEntity statsDetails) {
        StatEntity stats = statRepository.findByCharacterId(characterId)
                .orElseThrow(() -> new RuntimeException("Stats not found for character: " + characterId));

        stats.setStrength(statsDetails.getStrength());
        stats.setDexterity(statsDetails.getDexterity());
        stats.setConstitution(statsDetails.getConstitution());
        stats.setIntelligence(statsDetails.getIntelligence());
        stats.setWisdom(statsDetails.getWisdom());
        stats.setCharisma(statsDetails.getCharisma());

        stats.setUpdatedAt(OffsetDateTime.now());

        return statRepository.save(stats);
    }

    public StatEntity createStatsForCharacter(UUID characterId, StatEntity statsDetails) {
        StatEntity stats = new StatEntity();
        stats.setCharacter(characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found")));
        stats.setStrength(statsDetails.getStrength());
        stats.setDexterity(statsDetails.getDexterity());
        stats.setConstitution(statsDetails.getConstitution());
        stats.setIntelligence(statsDetails.getIntelligence());
        stats.setWisdom(statsDetails.getWisdom());
        stats.setCharisma(statsDetails.getCharisma());

        stats.setCreatedAt(OffsetDateTime.now());
        stats.setUpdatedAt(OffsetDateTime.now());

        return statRepository.save(stats);
    }
}