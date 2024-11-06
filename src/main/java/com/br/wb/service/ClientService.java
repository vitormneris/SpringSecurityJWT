package com.br.wb.service;

import java.util.*;

import com.br.wb.domain.Client;
import com.br.wb.enums.Values;
import com.br.wb.respositories.ClientRepository;
import com.br.wb.service.exceptions.DuplicateRecordException;
import com.br.wb.service.exceptions.InvalidFormatException;
import com.br.wb.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(String id) {
        try {
            Optional<Client> obj = repository.findById(id);
            return obj.orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Id", id);
        }
    }

    public Client findByEmail(String email) {
        try {
            Optional<Client> obj = repository.findByEmail(email);
            return obj.orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("E-mal", email);
        }
    }

    @Transactional
    public Client insert(Client client) {
        try {
            client.setId(null);

            if (repository.findByEmail(client.getEmail()).isPresent())
                throw new DuplicateRecordException("The e-mail is already registered");

            client.setRole(Values.CLIENT);
            client.setPassword(encoder.encode(client.getPassword()));
            return repository.save(client);
        } catch (InvalidFormatException e){
            throw new InvalidFormatException(e.getMessage());
        } catch (DuplicateRecordException e) {
            throw new DuplicateRecordException(e.getMessage());
        }
    }

    @Transactional
    public Client update(String id, Client client) {
        try {
            Optional<Client> obj = repository.findById(id);
            updateData(obj.orElseThrow(), client);
            obj.get().setId(id);

            return repository.save(obj.get());
        } catch (InvalidFormatException e){
            throw new InvalidFormatException(e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Id", id);
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            Optional<Client> objClient = repository.findById(id);
            repository.delete(objClient.orElseThrow());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Id", id);
        }
    }

    private void updateData(Client obj, Client client) {
        obj.setName((client.getName() == null) ? obj.getName() : client.getName());
        obj.setEmail((client.getEmail() == null) ? obj.getEmail() : client.getEmail());
    }
}
