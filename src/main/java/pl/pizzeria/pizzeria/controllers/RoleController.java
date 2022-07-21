package pl.pizzeria.pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.pizzeria.models.Delivery;
import pl.pizzeria.pizzeria.models.Role;
import pl.pizzeria.pizzeria.requests.request.NewDeliveryRequest;
import pl.pizzeria.pizzeria.services.RoleService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    /**
     * Create GET request to find role by id
     *
     * @param id id of role which user choose in form on frontend
     * @return if category with id exist return role with 200 request status if not
     *         exist return null with
     *         404 request status
     */
    @GetMapping("{id}")
    public ResponseEntity<Role> findById(@PathVariable UUID id) {
        Optional<Role> role = roleService.findById(id);

        return role.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<List<Role>> findAll() {
        List<Role> role = roleService.findAll();

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /**
     * Create POST request to save role
     *
     * @param role role to save which is in body of request
     * @return if save of role will be correct return role with 200 status code,
     *         if not return null with 400 status code
     */
    @PostMapping()
    public ResponseEntity<Role> save(@RequestBody Role role) {
        try {
            Role roleSave = roleService.save(role);
            return new ResponseEntity<>(roleSave, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

