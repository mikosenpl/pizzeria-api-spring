package pl.pizzeria.pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.pizzeria.models.Pizza;
import pl.pizzeria.pizzeria.requests.request.NewPizzaRequest;
import pl.pizzeria.pizzeria.services.PizzaService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<Pizza> findById(@PathVariable UUID id) {
        System.out.println(id);
        Optional<Pizza> pizza = pizzaService.findById(id);
        return pizza.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> findAll() {
        return new ResponseEntity<>(pizzaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NewPizzaRequest request) {
        try {
            Pizza pizza = modelMapper.map(request, Pizza.class);
            return new ResponseEntity<>(pizzaService.save(pizza), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody NewPizzaRequest request, @PathVariable UUID id) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        if (!pizza.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            pizza.get().setName(request.getName());
            pizza.get().setSmallPrice(request.getSmallPrice());
            pizza.get().setBigPrice(request.getBigPrice());
            pizza.get().setDescription(request.getDescription());

            return new ResponseEntity<>(pizzaService.update(pizza.get()), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            pizzaService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

