package com.aqe.dnd.dnd_catalog.features.character.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.dto.PartyDTO;
import com.aqe.dnd.dnd_catalog.features.character.repository.PartyRepository;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    // ====== MAPPER: Entity -> PartyDTO ======
    private PartyDTO toDTO(PartyEntity entity) {
        PartyDTO dto = new PartyDTO();
        dto.setId(entity.getPartyId());
        dto.setName(entity.getName());
        dto.setCampaignId(entity.getCampaignId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeletedAt(entity.getDeletedAt());
        return dto;
    }

    public PartyEntity getByName(String name) {
        return partyRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with name: " + name));
    }

    public PartyEntity getEntityById(UUID id) {
        return partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with ID: " + id));
    }

    public PartyDTO getDTOById(UUID id) {
        return toDTO(partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with ID: " + id)));
    }

    public PartyDTO create(PartyDTO party) {
        PartyEntity newParty = new PartyEntity();
        newParty.setName(party.getName());
        newParty.setCampaignId(party.getCampaignId());
        newParty.setCreatedAt(party.getCreatedAt() != null ? party.getCreatedAt() : OffsetDateTime.now());
        newParty.setUpdatedAt(party.getUpdatedAt() != null ? party.getUpdatedAt() : OffsetDateTime.now());
        return toDTO(partyRepository.save(newParty));
    }

    public void delete(UUID id) {
        partyRepository.deleteById(id);
    }

    public PartyDTO update(UUID id, PartyDTO party) {
        PartyEntity existingParty = partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with ID: " + id));

        existingParty.setName(party.getName());
        existingParty.setCampaignId(party.getCampaignId());

        return toDTO(partyRepository.save(existingParty));
    }

    public List<PartyDTO> getAllParties() {
        return partyRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

}
