package com.crud.client.services;

import com.crud.client.entities.Client;
import com.crud.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;


    public Page<Client> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return ResponseEntity.ok(result).getBody();
    }

    public Client findById(Long id) {
        Client client = repository.findById(id).get();
        return ResponseEntity.ok().body(client).getBody();
    }
}
