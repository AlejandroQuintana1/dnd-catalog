package com.aqe.dnd.dnd_catalog.features.skill.service;

import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;
import com.aqe.dnd.dnd_catalog.features.character.service.CharacterService;
import com.aqe.dnd.dnd_catalog.features.skill.domain.CharacterSkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.domain.SkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.dto.CharacterSkillDTO;
import com.aqe.dnd.dnd_catalog.features.skill.repository.CharacterSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterSkillService {

    private final CharacterSkillRepository characterSkillRepository;
    private final CharacterService characterService;
    private final SkillService skillService;

    public CharacterSkillEntity assignSkill(CharacterSkillDTO dto) {
        // CharacterEntity character = characterService.get(dto.getCharacterId());
        SkillEntity skill;

        if (dto.getSkillId() != null) {
            skill = skillService.getById(dto.getSkillId());
        } else {
            skill = skillService.getByCode(dto.getSkillCode());
        }

        CharacterSkillEntity characterSkill = new CharacterSkillEntity();
        characterSkill.setCharacterId(dto.getCharacterId());
        characterSkill.setSkillId(skill.getId());
        characterSkill.setProficient(dto.getProficient());

        return characterSkillRepository.save(characterSkill);
    }

    public List<CharacterSkillEntity> getCharacterSkills(UUID characterId) {
        return characterSkillRepository.findByCharacterId(characterId);
    }
}
