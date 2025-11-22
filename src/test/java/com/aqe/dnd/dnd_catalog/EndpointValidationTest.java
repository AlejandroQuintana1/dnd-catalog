package com.aqe.dnd.dnd_catalog;

import com.aqe.dnd.dnd_catalog.features.character.controller.CharacterController;
import com.aqe.dnd.dnd_catalog.features.character.controller.PartyController;
import com.aqe.dnd.dnd_catalog.features.character.domain.CharacterEntity;
import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.dto.CharacterDTO;
import com.aqe.dnd.dnd_catalog.features.character.service.CharacterService;
import com.aqe.dnd.dnd_catalog.features.character.service.PartyService;
import com.aqe.dnd.dnd_catalog.features.item.controller.ItemController;
import com.aqe.dnd.dnd_catalog.features.item.domain.ItemEntity;
import com.aqe.dnd.dnd_catalog.features.item.dto.ItemDTO;
import com.aqe.dnd.dnd_catalog.features.item.service.ItemService;
import com.aqe.dnd.dnd_catalog.features.skill.controller.CharacterSkillController;
import com.aqe.dnd.dnd_catalog.features.skill.controller.SkillController;
import com.aqe.dnd.dnd_catalog.features.skill.service.CharacterSkillService;
import com.aqe.dnd.dnd_catalog.features.skill.service.SkillService;
import com.aqe.dnd.dnd_catalog.features.spell.controller.SpellController;
import com.aqe.dnd.dnd_catalog.features.spell.service.SpellService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
        PartyController.class,
        ItemController.class,
        CharacterController.class,
        SkillController.class,
        CharacterSkillController.class,
        SpellController.class
})
public class EndpointValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartyService partyService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private SkillService skillService;

    @MockBean
    private CharacterSkillService characterSkillService;

    @MockBean
    private SpellService spellService;

    @Test
    public void testCreateParty() throws Exception {
        PartyEntity party = new PartyEntity();
        party.setName("Test Party");

        // Mock service to return the party
        when(partyService.create(any(PartyEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // This test is expected to fail or behave unexpectedly if the @RequestBody
        // annotation is wrong
        // If the controller receives null, it might throw an exception or pass null to
        // service
        mockMvc.perform(post("/characters/parties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(party)))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    // Verify that the service was called with the correct name
                    // If binding failed, the name will be null
                    org.mockito.Mockito.verify(partyService).create(org.mockito.ArgumentMatchers
                            .argThat(p -> p.getName() != null && p.getName().equals("Test Party")));
                });
    }

    @Test
    public void testCreateItem() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Test Item");

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName("Test Item");

        when(itemService.create(any(ItemDTO.class))).thenReturn(itemEntity);

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isCreated());
    }
}
