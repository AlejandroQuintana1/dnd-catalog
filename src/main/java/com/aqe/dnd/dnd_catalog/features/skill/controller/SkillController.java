package com.aqe.dnd.dnd_catalog.features.skill.controller;

import com.aqe.dnd.dnd_catalog.features.skill.domain.SkillEntity;
import com.aqe.dnd.dnd_catalog.features.skill.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @Operation(summary = "Get all skills")
    @ApiResponse(responseCode = "200", description = "List of all skills")
    @GetMapping
    public ResponseEntity<List<SkillEntity>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAll());
    }
}
