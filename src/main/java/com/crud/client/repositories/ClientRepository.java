package com.crud.client.repositories;

import com.crud.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
