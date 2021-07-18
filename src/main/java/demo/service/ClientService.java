package demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import demo.dto.ClientDTO;
import demo.entity.Client;
import demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper mapper;

    public ClientDTO getClient(Long id) {
gi        var client = clientRepository.getById(id);
        var clientDTO = toDTO(client);
        return clientDTO;
    }

    public ClientDTO toDTO(Client client) {
        var clientDTO = mapper.convertValue(client, ClientDTO.class);
        return clientDTO;
    }

    public ClientDTO create(ClientDTO clientDTO) {
        clientDTO.setLogin(generateLogin(clientDTO));
        var client = mapper.convertValue(clientDTO, Client.class);
        client.setPassword("root");
        var clientSaved = clientRepository.save(client);
        return toDTO(clientSaved);
    }

    public String generateLogin(ClientDTO clientDTO) {
        StringBuilder sb = new StringBuilder();
        var random = new Random();
        var randomInt = random.nextInt(25);
        return sb.append(clientDTO.getFirstName()).append("_")
                .append(clientDTO.getLastName())
                .append(randomInt).toString();
    }
}
