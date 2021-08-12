package demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import demo.data.ResponseStatus;
import demo.dto.ClientDTO;
import demo.entity.Client;
import demo.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clients", description = "Create, Get, Update, Delete Client")
@AllArgsConstructor
@RequestMapping("/clients")
@RestController
public class ClientController {

    private final ClientService clientService;
    private final ObjectMapper mapper;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long clientId) {
        var client = clientService.getById(clientId);
        var clientDTO = mapper.convertValue(client, ClientDTO.class);
        return ResponseEntity.ok(clientDTO);
    }

    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя")
    @PostMapping("/")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        var client = updateMapper().convertValue(clientDTO, Client.class);
        var clientCreated = clientService.create(client);
        var clientDTOCreated = updateMapper().convertValue(clientCreated, ClientDTO.class);
        return ResponseEntity.ok(clientDTOCreated);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientDTO> update(@RequestParam Long clientId, @RequestBody ClientDTO clientDTO) throws JsonProcessingException {
        var client = clientService.getById(clientId);
        if (client == null) {
            throw new ObjectNotFoundException(null, "client not found");
        }
        if (clientDTO.getSnils() != null) {
            client.setSnils(clientDTO.getSnils());
        }
        if (clientDTO.getPassword() != null) {
            client.setPassword(clientDTO.getPassword());
        }
        var clientUpdatedReturn = clientService.update(client);
        var clientDTOupdated = mapper.convertValue(clientUpdatedReturn, ClientDTO.class);
        return ResponseEntity.ok(clientDTOupdated);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<ResponseStatus> delete(@RequestParam Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.ok(new ResponseStatus("ok", "deleted client " + clientId));
    }

        public ObjectMapper updateMapper() {
        return mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
