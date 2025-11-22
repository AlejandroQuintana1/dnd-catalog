package com.aqe.dnd.dnd_catalog.features.skill.service;

import com.aqe.dnd.dnd_catalog.features.skill.domain.SkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillEntity getByCode(String code) {
        return skillRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Skill not found with code: " + code));
    }

    public SkillEntity getById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Skill not found with id: " + id));
    }

    public List<SkillEntity> getAll() {
        return skillRepository.findAll();
    }
}
