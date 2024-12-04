package com.crud.client.services;

import com.crud.client.entities.Client;
import com.crud.client.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return ResponseEntity.ok(result).getBody();
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        Client client = repository.findById(id).get();
        return ResponseEntity.ok().body(client).getBody();
    }

    @Transactional
    public Client insert(Client client) {
        Client entity = new Client(client);
        copyClientProperties(entity, client);
        entity = repository.save(entity);
        return new Client(entity);
    }

    @Transactional
    public Client update(Long id, Client client) {
        try {
            Client entity = repository.getReferenceById(id);
            copyClientProperties(entity, client);
            entity = repository.save(entity);
            return new Client(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }


    private void copyClientProperties(Client entity, Client client) {
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setIncome(client.getIncome());
        entity.setBirthDate(client.getBirthDate());
        entity.setChildren(client.getChildren());
    }
}
