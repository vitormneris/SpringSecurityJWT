package com.br.wb.mapper;

import com.br.wb.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.br.wb.dto.ClientDTO;

@Component
@RequiredArgsConstructor
public class ClientMapper {
    public Client mapToModel(ClientDTO dto) {
        return new Client(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.password()
        );
    }
}
