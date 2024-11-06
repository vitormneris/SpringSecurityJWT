package com.br.wb.controller;

import java.net.URI;
import java.util.List;

import com.br.wb.domain.Client;
import com.br.wb.dto.ClientDTO;
import com.br.wb.mapper.ClientMapper;
import com.br.wb.service.ClientService;
import com.br.wb.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService  service;
    private final ClientMapper  mapper;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/token")
    public ResponseEntity<Client> findByToken(@RequestHeader("Authorization") String token) {
        Client obj = service.findById(tokenService.getClaimId(token));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id) {
        Client obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Client> findByEmail(@PathVariable String email) {
        Client obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody ClientDTO clientDTO) {
        Client client = mapper.mapToModel(clientDTO);
        Client obj = service.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/token")
    public ResponseEntity<Client> updateByToken(@RequestHeader("Authorization") String token, @RequestBody ClientDTO clientDTO) {
        Client client = mapper.mapToModel(clientDTO);
        Client obj = service.update(tokenService.getClaimId(token), client);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        Client client = mapper.mapToModel(clientDTO);
        Client obj = service.update(id, client);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/token")
    public ResponseEntity<Void> deleteByToken(@RequestHeader("Authorization") String token) {
        service.delete(tokenService.getClaimId(token));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



