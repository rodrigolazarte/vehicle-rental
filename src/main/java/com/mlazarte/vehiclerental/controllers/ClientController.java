package com.mlazarte.vehiclerental.controllers;

import com.mlazarte.vehiclerental.models.Client;
import com.mlazarte.vehiclerental.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping(
            value = "/client",
            produces = "application/json"
    )
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> listOfClients = clientRepository.findAll();
        return ResponseEntity.ok().body(listOfClients);
    }

    @GetMapping(
            value = "/client/{id}",
            produces = {"application/json"}
    )
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) return ResponseEntity.ok().body(client.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/client",
            produces = "application/json"
    )
    public ResponseEntity<Client> saveNewClient(@RequestBody Client client) {
        clientRepository.save(client);
        URI uri = URI.create("/api/client/" + client.getClientId());
        return ResponseEntity.created(uri).body(client);
    }
}
