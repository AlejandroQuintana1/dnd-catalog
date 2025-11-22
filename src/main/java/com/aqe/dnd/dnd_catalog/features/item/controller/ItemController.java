package com.aqe.dnd.dnd_catalog.features.item.controller;

import com.aqe.dnd.dnd_catalog.features.item.domain.ItemEntity;
import com.aqe.dnd.dnd_catalog.features.item.dto.ItemDTO;
import com.aqe.dnd.dnd_catalog.features.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Create a new item")
    @ApiResponse(responseCode = "201", description = "Item created successfully")
    @PostMapping
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDTO itemDTO) {
        ItemEntity createdItem = itemService.create(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @Operation(summary = "Get an item by ID")
    @ApiResponse(responseCode = "200", description = "Item details")
    @ApiResponse(responseCode = "404", description = "Item not found")
    @GetMapping("/{id}")
    public ResponseEntity<ItemEntity> getItem(@PathVariable UUID id) {
        ItemEntity item = itemService.get(id);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Update an item")
    @ApiResponse(responseCode = "200", description = "Item updated")
    @PutMapping("/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable UUID id, @RequestBody ItemDTO itemDTO) {
        ItemEntity updatedItem = itemService.update(id, itemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @Operation(summary = "Delete an item")
    @ApiResponse(responseCode = "204", description = "Item deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all items")
    @ApiResponse(responseCode = "200", description = "List of all items")
    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItems() {
        List<ItemEntity> items = itemService.getAll();
        return ResponseEntity.ok(items);
    }
}
