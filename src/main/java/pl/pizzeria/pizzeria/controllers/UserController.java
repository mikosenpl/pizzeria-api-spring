package pl.pizzeria.pizzeria.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import pl.pizzeria.pizzeria.models.User;
import pl.pizzeria.pizzeria.requests.request.NewUserRequest;
import pl.pizzeria.pizzeria.requests.request.UpdateUserPasswordRequest;
import pl.pizzeria.pizzeria.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Optional<User> u = userService.findByEmail(email);
        if (u.isPresent())
            return new ResponseEntity<>(u.get(), HttpStatus.OK);
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@Valid @RequestBody NewUserRequest request, @PathVariable UUID id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            user.get().setName(request.getName());
            user.get().setEmail(request.getEmail());
            return new ResponseEntity<>(userService.update(user.get()), HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UpdateUserPasswordRequest request) {
        Optional<User> user = userService.findById(request.getId());
        if (!user.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try {
            if (!passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())) {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "Obecne hasło jest niepoprawne");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            String hashedPass = passwordEncoder.encode(request.getPassword());
            user.get().setPassword(hashedPass);
            userService.update(user.get());

            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IllegalArgumentException | MappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

