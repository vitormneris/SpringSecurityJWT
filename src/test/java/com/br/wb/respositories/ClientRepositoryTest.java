//package com.br.wb.respositories;
//
//import com.br.wb.domain.Address;
//import com.br.wb.domain.Client;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataMongoTest
//@ActiveProfiles
//class ClientRepositoryTest {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    private Client createClient(Client client) {
//        List<String> phones = Arrays.asList("11955696863", "11955696864", "11955696865");
//        Client.RNMDocument rnm = new Client.RNMDocument("12345640", "classificação", "11/10/2005");
//        Address address = new Address("São Paulo", "São paulo", "Brás", "Rua da custura", "01111-000", 1689);
//        return new Client(null, "Boliva Safado", "boliva@gmail.com", encoder.encode("1234"), "111.111.111-111", rnm, "Bolivia", phones, address);
//    }
//
//
//
//    @Test
//    void findByEmail() {
//        clientRepository.findByEmail("");
//    }
//
//    @Test
//    void findByCpf() {
//    }
//}