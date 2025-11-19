package com.aqe.dnd.dnd_catalog.features.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterTypeEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.StatEntity;
import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterDTO;
import com.aqe.dnd.dnd_catalog.features.character.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private StatService statService;

    @Autowired
    private CharacterTypeService characterTypeService;

    @Autowired
    private PartyService partyService;

    // Crear personaje
    public CharacterEntity create(CharacterDTO characterDTO) {
        // Crear el personaje
        CharacterEntity newCharacter = new CharacterEntity();
        newCharacter.setName(characterDTO.getName());
        newCharacter.setRace(characterDTO.getRace());
        newCharacter.setCharacterClass(characterDTO.getCharacterClass());
        newCharacter.setLevel(characterDTO.getLevel());
        newCharacter.setExperience(characterDTO.getExperience());
        newCharacter.setHitPoints(characterDTO.getHitPoints());
        newCharacter.setArmorClass(characterDTO.getArmorClass());
        newCharacter.setInitiativeMisc(characterDTO.getInitiativeMisc());
        newCharacter.setNotes(characterDTO.getNotes());
        newCharacter.setGrp(characterDTO.getGrp());

        // Actualizar las relaciones
        if (characterDTO.getCharacterTypeCode() != null) {
            CharacterTypeEntity characterType = characterTypeService.getByCode(characterDTO.getCharacterTypeCode());
            newCharacter.setCharacterType(characterType);
        }

        if (characterDTO.getPartyId() != null) {
            PartyEntity party = partyService.getById(characterDTO.getPartyId());
            newCharacter.setParty(party);
        }
        // Asignar fechas automáticas
        newCharacter.setCreatedAt(OffsetDateTime.now());
        newCharacter.setUpdatedAt(OffsetDateTime.now());

        // Guardamos el personaje
        newCharacter = characterRepository.save(newCharacter);

        // Crear las estadísticas asociadas
        StatEntity stats = new StatEntity();
        stats.setCharacter(newCharacter); // Asociamos al personaje
        stats.setStrength(characterDTO.getStrength());
        stats.setDexterity(characterDTO.getDexterity());
        stats.setConstitution(characterDTO.getConstitution());
        stats.setIntelligence(characterDTO.getIntelligence());
        stats.setWisdom(characterDTO.getWisdom());
        stats.setCharisma(characterDTO.getCharisma());
        stats.setCreatedAt(OffsetDateTime.now());
        stats.setUpdatedAt(OffsetDateTime.now());

        // Guardamos las estadísticas
        statService.createStatsForCharacter(newCharacter.getId(), stats);

        return newCharacter;
    }

    // Obtener personaje por ID
    public CharacterEntity get(UUID id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Character not found"));
    }

    // Actualizar personaje
    public CharacterEntity update(UUID id, CharacterDTO characterDTO) {
        // Buscar el personaje por ID
        CharacterEntity character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));

        // Actualizamos los campos modificables usando el DTO
        character.setName(characterDTO.getName());
        character.setRace(characterDTO.getRace());
        character.setCharacterClass(characterDTO.getCharacterClass());
        character.setLevel(characterDTO.getLevel());
        character.setExperience(characterDTO.getExperience());
        character.setHitPoints(characterDTO.getHitPoints());
        character.setArmorClass(characterDTO.getArmorClass());
        character.setInitiativeMisc(characterDTO.getInitiativeMisc());
        // character.setNotes(characterDTO.getNotes());

        // No actualizamos las fechas, se gestionan automáticamente en el modelo
        character.setUpdatedAt(OffsetDateTime.now());

        // Actualizamos las estadísticas si vienen en el DTO
        if (characterDTO.getStrength() != null && characterDTO.getDexterity() != null &&
                characterDTO.getConstitution() != null && characterDTO.getIntelligence() != null &&
                characterDTO.getWisdom() != null && characterDTO.getCharisma() != null) {

            StatEntity stats = new StatEntity();
            stats.setStrength(characterDTO.getStrength());
            stats.setDexterity(characterDTO.getDexterity());
            stats.setConstitution(characterDTO.getConstitution());
            stats.setIntelligence(characterDTO.getIntelligence());
            stats.setWisdom(characterDTO.getWisdom());
            stats.setCharisma(characterDTO.getCharisma());

            statService.updateStats(id, stats); // Llamamos a StatService para actualizar las stats
        }

        // Guardamos el personaje actualizado
        return characterRepository.save(character);
    }

    // Eliminar personaje (soft delete)
    public void delete(UUID id) {
        CharacterEntity character = get(id);
        character.setDeletedAt(OffsetDateTime.now()); // Marca como eliminado (soft delete)
        characterRepository.save(character);
    }

    // Obtener lista de personajes activos
    public List<CharacterEntity> getAll() {
        return characterRepository.findByDeletedAtIsNull();
    }
}