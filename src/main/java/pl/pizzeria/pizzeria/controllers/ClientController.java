package pl.pizzeria.pizzeria.controllers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.pizzeria.models.Client;
import pl.pizzeria.pizzeria.requests.request.NewClientRequest;
import pl.pizzeria.pizzeria.services.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<Client> findById(@PathVariable UUID id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(clientService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NewClientRequest request) {
        try {
            Client client = modelMapper.map(request, Client.class);
            return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
        }
        catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@Valid @RequestBody NewClientRequest request, @PathVariable UUID id) {
        Optional<Client> client = clientService.findById(id);
        if(!client.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            client.get().setName(request.getName());
            client.get().setLastName(request.getLastName());
            client.get().setEmail(request.getEmail());
            client.get().setPhone(request.getPhone());
            client.get().setAddress(request.getAddress());
            return new ResponseEntity<>(clientService.update(client.get()), HttpStatus.OK);
        }
        catch (IllegalArgumentException | MappingException e ) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            clientService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
