package com.aqe.dnd.dnd_catalog.features.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterTypeEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.StatEntity;
import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterDTO;
import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterResponseDTO;
import com.aqe.dnd.dnd_catalog.features.character.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // ====== MAPPER: Entity -> ResponseDTO ======
    private CharacterResponseDTO toResponseDTO(CharacterEntity entity) {
        CharacterResponseDTO dto = new CharacterResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRace(entity.getRace());
        dto.setCharacterClass(entity.getCharacterClass());
        dto.setLevel(entity.getLevel());
        dto.setBackground(entity.getBackground());
        dto.setAlignment(entity.getAlignment());
        dto.setExperience(entity.getExperience());
        dto.setHitPoints(entity.getHitPoints());
        dto.setArmorClass(entity.getArmorClass());
        dto.setInitiativeMisc(entity.getInitiativeMisc());
        dto.setNotes(entity.getNotes());
        dto.setGrp(entity.getGrp());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeletedAt(entity.getDeletedAt());

        // IMPORTANTE: aquí NO tocamos characterType ni party para no disparar LAZY

        return dto;
    }

    // ====== Crear personaje ======
    public CharacterResponseDTO create(CharacterDTO characterDTO) {
        CharacterEntity newCharacter = new CharacterEntity();

        // 1) Gestionar el id
        if (characterDTO.getId() != null) {
            // Comprobamos que no exista ya ese id
            if (characterRepository.existsById(characterDTO.getId())) {
                throw new IllegalStateException("Character with id " + characterDTO.getId() + " already exists");
                // aquí podrías mapear luego a un 409 CONFLICT
            }
            newCharacter.setId(characterDTO.getId());
        } else {
            // Si no viene id, generamos uno
            newCharacter.setId(UUID.randomUUID());
        }

        // 2) Marcamos explícitamente que es nuevo para Spring Data
        newCharacter.markNew();

        // 3) Setear resto de campos
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
        newCharacter.setBackground(characterDTO.getBackground());
        newCharacter.setAlignment(characterDTO.getAlignment());

        if (characterDTO.getCharacterTypeCode() != null) {
            CharacterTypeEntity characterType = characterTypeService.getByCode(characterDTO.getCharacterTypeCode());
            newCharacter.setCharacterType(characterType);
        }

        if (characterDTO.getPartyId() != null) {
            PartyEntity party = partyService.getEntityById(characterDTO.getPartyId());
            newCharacter.setParty(party);
        }

        newCharacter.setCreatedAt(OffsetDateTime.now());
        newCharacter.setUpdatedAt(OffsetDateTime.now());

        // 4) Guardar el personaje (Spring ahora hará persist/INSERT)
        newCharacter = characterRepository.save(newCharacter);

        // 5) Stats
        StatEntity stats = new StatEntity();
        stats.setCharacter(newCharacter);
        stats.setStrength(characterDTO.getStrength());
        stats.setDexterity(characterDTO.getDexterity());
        stats.setConstitution(characterDTO.getConstitution());
        stats.setIntelligence(characterDTO.getIntelligence());
        stats.setWisdom(characterDTO.getWisdom());
        stats.setCharisma(characterDTO.getCharisma());
        stats.setCreatedAt(OffsetDateTime.now());
        stats.setUpdatedAt(OffsetDateTime.now());

        statService.createStatsForCharacter(newCharacter.getId(), stats);

        return toResponseDTO(newCharacter);
    }

    // ====== Obtener entidad interna (para uso dentro del servicio) ======
    private CharacterEntity getEntity(UUID id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Character not found"));
    }

    // ====== Obtener personaje por ID (para la API) ======
    public CharacterResponseDTO get(UUID id) {
        CharacterEntity entity = getEntity(id);
        return toResponseDTO(entity);
    }

    // ====== Actualizar personaje ======
    public CharacterResponseDTO update(UUID id, CharacterDTO characterDTO) {
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
        // Si quieres permitir actualizar notas/grp/background/alignment, añade aquí:
        // character.setNotes(characterDTO.getNotes());
        // character.setGrp(characterDTO.getGrp());
        // character.setBackground(characterDTO.getBackground());
        // character.setAlignment(characterDTO.getAlignment());

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
        CharacterEntity updated = characterRepository.save(character);

        // Devolvemos el DTO de respuesta
        return toResponseDTO(updated);
    }

    // ====== Eliminar personaje (soft delete) ======
    public void delete(UUID id) {
        CharacterEntity character = getEntity(id);
        character.setDeletedAt(OffsetDateTime.now()); // Marca como eliminado (soft delete)
        characterRepository.save(character);
    }

    // ====== Obtener lista de personajes activos ======
    public List<CharacterResponseDTO> getAll() {
        return characterRepository.findByDeletedAtIsNull()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
