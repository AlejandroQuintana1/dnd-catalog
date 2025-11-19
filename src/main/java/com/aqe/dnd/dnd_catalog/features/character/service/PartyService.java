package com.aqe.dnd.dnd_catalog.features.character.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqe.dnd.dnd_catalog.features.character.domain.PartyEntity;
import com.aqe.dnd.dnd_catalog.features.character.repository.PartyRepository;

@Service
public class PartyService {
    
    @Autowired
    private PartyRepository partyRepository;

    public PartyEntity getByName(String name) {
        return partyRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with name: " + name));
    }

    public PartyEntity getById(UUID id) {
        return partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with ID: " + id));
    }

    public PartyEntity create(PartyEntity party) {
        return partyRepository.save(party);
    }

    public void delete(UUID id) {
        partyRepository.deleteById(id);
    }

    public PartyEntity update(UUID id, PartyEntity party) {
        PartyEntity existingParty = partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party not found with ID: " + id));

        existingParty.setName(party.getName());
        existingParty.setCampaingId(party.getCampaingId());

        return partyRepository.save(existingParty);
    }

    public List<PartyEntity> getAllParties() {
        return partyRepository.findAll();
    }

}
