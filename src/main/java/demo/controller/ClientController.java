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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Название контроллера", description = "Описание контролера")
@AllArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientController {

    private final ClientService clientService;
    private final ObjectMapper mapper;

    @GetMapping("/get/{clientId}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long clientId) {
        var client = clientService.getById(clientId);
        var clientDTO = mapper.convertValue(client, ClientDTO.class);
        return ResponseEntity.ok(clientDTO);
    }

    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя")
    @PostMapping("/create/")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
//        clientService.findByEmail(clientDTO.getEmail());
        var client = updateMapper().convertValue(clientDTO, Client.class);
        var clientCreated = clientService.create(client);
        var clientDTOCreated = updateMapper().convertValue(clientCreated, ClientDTO.class);
        return ResponseEntity.ok(clientDTOCreated);
    }

    @PatchMapping("/update/{clientId}")
    public ResponseEntity<ClientDTO> update(@RequestParam Long clientId, @RequestBody ClientDTO clientDTO) throws JsonProcessingException {
        var client = clientService.getById(clientId);
        var clientUpdated = updateMapper().readerForUpdating(client).readValue(mapper.writeValueAsString(clientDTO));
        var clientUpdatedReturn = clientService.update(client);
        var clientDTOupdated = mapper.convertValue(clientUpdatedReturn, ClientDTO.class);
        return ResponseEntity.ok(clientDTOupdated);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<ResponseStatus> delete(@RequestParam Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.ok(new ResponseStatus("ok", "deleted client " + clientId));
    }

        public ObjectMapper updateMapper() {
        return mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
