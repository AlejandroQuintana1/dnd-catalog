package com.aqe.dnd.dnd_catalog.features.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterDTO;
import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterResponseDTO;
import com.aqe.dnd.dnd_catalog.features.character.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Operation(summary = "Obtener todos los personajes activos")
    @ApiResponse(responseCode = "200", description = "Lista de personajes activos")
    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAll());
    }

    @Operation(summary = "Obtener un personaje por ID")
    @ApiResponse(responseCode = "200", description = "Detalles del personaje")
    @ApiResponse(responseCode = "404", description = "Personaje no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> getCharacter(@PathVariable UUID id) {
        return ResponseEntity.ok(characterService.get(id));
    }

    @Operation(summary = "Crear un nuevo personaje")
    @ApiResponse(responseCode = "201", description = "Personaje creado con éxito")
    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createCharacter(@RequestBody CharacterDTO character) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.create(character));
    }

    @Operation(summary = "Actualizar un personaje")
    @ApiResponse(responseCode = "200", description = "Personaje actualizado")
    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(@PathVariable UUID id,
            @RequestBody CharacterDTO character) {
        return ResponseEntity.ok(characterService.update(id, character));
    }

    @Operation(summary = "Eliminar un personaje")
    @ApiResponse(responseCode = "204", description = "Personaje eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
