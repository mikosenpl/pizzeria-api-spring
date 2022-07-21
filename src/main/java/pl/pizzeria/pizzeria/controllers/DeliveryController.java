package pl.pizzeria.pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.pizzeria.models.Delivery;
import pl.pizzeria.pizzeria.requests.request.NewClientRequest;
import pl.pizzeria.pizzeria.requests.request.NewDeliveryRequest;
import pl.pizzeria.pizzeria.services.DeliveryService;
import pl.pizzeria.pizzeria.services.interfaces.IClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<Delivery> findById(@PathVariable UUID id) {
        System.out.println(id);
        Optional<Delivery> delivery = deliveryService.findById(id);
        return delivery.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> findAll() {
        return new ResponseEntity<>(deliveryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NewDeliveryRequest request) {
        try {
            Delivery delivery = modelMapper.map(request, Delivery.class);
            return new ResponseEntity<>(deliveryService.save(delivery), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Delivery> update(@Valid @RequestBody NewDeliveryRequest request, @PathVariable UUID id) {
        Optional<Delivery> delivery = deliveryService.findById(id);
        if (!delivery.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            delivery.get().setDeliveryStatus(request.isDeliveryStatus());
            delivery.get().setDeliveryTime(request.getDeliveryTime());

            return new ResponseEntity<>(deliveryService.update(delivery.get()), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            deliveryService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
