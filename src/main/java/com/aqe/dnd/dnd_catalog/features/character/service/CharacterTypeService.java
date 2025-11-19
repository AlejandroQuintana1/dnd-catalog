package com.aqe.dnd.dnd_catalog.features.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterTypeEntity;
import com.aqe.dnd.dnd_catalog.features.character.repository.CharacterTypeRepository;

@Service
public class CharacterTypeService {
    
    @Autowired
    private CharacterTypeRepository characterTypeRepository;

    public CharacterTypeEntity getByCode(String code) {
        return characterTypeRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de personaje no encontrado con el código: " + code));
    }

    public CharacterTypeEntity create(CharacterTypeEntity characterType) {
        return characterTypeRepository.save(characterType);
    }

    public void delete(Long id) {
        characterTypeRepository.deleteById(id);
    }

    public CharacterTypeEntity update(Long id, CharacterTypeEntity characterType) {
        CharacterTypeEntity existingCharacterType = characterTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de personaje no encontrado con el ID: " + id));

        existingCharacterType.setCode(characterType.getCode());
        existingCharacterType.setDescription(characterType.getDescription());

        return characterTypeRepository.save(existingCharacterType);
    }
    
}
