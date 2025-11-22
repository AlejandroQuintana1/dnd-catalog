package com.aqe.dnd.dnd_catalog.features.skill.controller;

import com.aqe.dnd.dnd_catalog.features.skill.domain.CharacterSkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.dto.CharacterSkillDTO;
import com.aqe.dnd.dnd_catalog.features.skill.service.CharacterSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/character-skills")
@RequiredArgsConstructor
public class CharacterSkillController {

    private final CharacterSkillService characterSkillService;

    @Operation(summary = "Assign a skill to a character")
    @ApiResponse(responseCode = "201", description = "Skill assigned successfully")
    @PostMapping
    public ResponseEntity<CharacterSkillEntity> assignSkill(@RequestBody CharacterSkillDTO dto) {
        CharacterSkillEntity assignedSkill = characterSkillService.assignSkill(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedSkill);
    }

    @Operation(summary = "Get skills of a character")
    @ApiResponse(responseCode = "200", description = "List of character skills")
    @GetMapping("/{characterId}")
    public ResponseEntity<List<CharacterSkillEntity>> getCharacterSkills(@PathVariable UUID characterId) {
        return ResponseEntity.ok(characterSkillService.getCharacterSkills(characterId));
    }
}
