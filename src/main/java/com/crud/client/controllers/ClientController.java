package com.crud.client.controllers;

import com.crud.client.entities.Client;
import com.crud.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping
    public ResponseEntity<Page<Client>> findAll(Pageable pageable) {
        Page<Client> result = clientService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }
}
