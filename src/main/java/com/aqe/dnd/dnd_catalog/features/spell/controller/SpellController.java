package com.aqe.dnd.dnd_catalog.features.spell.controller;

import com.aqe.dnd.dnd_catalog.features.spell.domain.SpellEntity;
import com.aqe.dnd.dnd_catalog.features.spell.dto.SpellDTO;
import com.aqe.dnd.dnd_catalog.features.spell.service.SpellService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/spells")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @Operation(summary = "Create a new spell")
    @ApiResponse(responseCode = "201", description = "Spell created successfully")
    @PostMapping
    public ResponseEntity<SpellEntity> createSpell(@RequestBody SpellDTO spellDTO) {
        SpellEntity createdSpell = spellService.create(spellDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpell);
    }

    @Operation(summary = "Get a spell by ID")
    @ApiResponse(responseCode = "200", description = "Spell details")
    @ApiResponse(responseCode = "404", description = "Spell not found")
    @GetMapping("/{id}")
    public ResponseEntity<SpellEntity> getSpell(@PathVariable UUID id) {
        SpellEntity spell = spellService.get(id);
        return ResponseEntity.ok(spell);
    }

    @Operation(summary = "Update a spell")
    @ApiResponse(responseCode = "200", description = "Spell updated")
    @PutMapping("/{id}")
    public ResponseEntity<SpellEntity> updateSpell(@PathVariable UUID id, @RequestBody SpellDTO spellDTO) {
        SpellEntity updatedSpell = spellService.update(id, spellDTO);
        return ResponseEntity.ok(updatedSpell);
    }

    @Operation(summary = "Delete a spell")
    @ApiResponse(responseCode = "204", description = "Spell deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpell(@PathVariable UUID id) {
        spellService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all spells")
    @ApiResponse(responseCode = "200", description = "List of all spells")
    @GetMapping
    public ResponseEntity<List<SpellEntity>> getAllSpells() {
        List<SpellEntity> spells = spellService.getAll();
        return ResponseEntity.ok(spells);
    }
}
