package com.aqe.dnd.dnd_catalog.features.character.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.dto.PartyDTO;
import com.aqe.dnd.dnd_catalog.features.character.service.PartyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/characters/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @Operation(summary = "Obtener todos los las partys")
    @ApiResponse(responseCode = "200", description = "Lista de partys")
    @GetMapping
    public List<PartyDTO> getAllParties() {
        return partyService.getAllParties();
    }

    @Operation(summary = "Obtener una party por ID")
    @ApiResponse(responseCode = "200", description = "Detalles de la paty")
    @ApiResponse(responseCode = "404", description = "Party no encontrada")
    @GetMapping("/{id}")
    public PartyDTO getPartyById(@PathVariable UUID id) {
        return partyService.getDTOById(id);
    }

    @Operation(summary = "Crear una nueva party")
    @ApiResponse(responseCode = "201", description = "Party creada con éxito")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartyDTO createParty(@RequestBody PartyDTO party) {
        return partyService.create(party);
    }

    @Operation(summary = "Eliminar una party")
    @ApiResponse(responseCode = "204", description = "Party eliminada")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParty(@PathVariable UUID id) {
        partyService.delete(id);
    }

    @Operation(summary = "Actualizar una party")
    @ApiResponse(responseCode = "200", description = "Party actualizada")
    @PutMapping("/{id}")
    public PartyDTO updateParty(@PathVariable UUID id, @RequestBody PartyDTO party) {
        return partyService.update(id, party);
    }

}
