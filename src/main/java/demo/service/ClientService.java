package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.entity.Client;
import demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@AllArgsConstructor
@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper mapper;

    @Transactional
    public Client getById(Long id) {
        return clientRepository.getById(id);
    }

    @Transactional
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client findByEmail(String email) {
        var clientOp = clientRepository.findByEmail(email);
        return clientOp.get();
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client update(Client client) {
        return clientRepository.save(client);
    }


    public String generateLogin(Client client) {
        StringBuilder sb = new StringBuilder();
        var random = new Random();
        var randomInt = random.nextInt(25);
        return sb.append(client.getFirstName()).append("_")
                .append(client.getLastName())
                .append(randomInt).toString();
    }
}
