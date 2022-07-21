package pl.pizzeria.pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.pizzeria.models.Order;
import pl.pizzeria.pizzeria.requests.request.NewOrderRequest;
import pl.pizzeria.pizzeria.services.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable UUID id) {
        System.out.println(id);
        Optional<Order> order = orderService.findById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NewOrderRequest request) {
        try {
            Order order = modelMapper.map(request, Order.class);
            return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Order> update(@Valid @RequestBody NewOrderRequest request, @PathVariable UUID id) {
        Optional<Order> order = orderService.findById(id);
        if (!order.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            order.get().setTotalPrice(request.getTotalPrice());
            order.get().setDescription(request.getDescription());
            order.get().setOrderTime(request.getOrderTime());


            return new ResponseEntity<>(orderService.update(order.get()), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            orderService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
