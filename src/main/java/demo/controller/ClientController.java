package demo.controller;

import demo.dto.ClientDTO;
import demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long clientId) {
        var client = clientService.getClient(clientId);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/")
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        var client = clientService.create(clientDTO);
        return ResponseEntity.ok(client);
    }
}
