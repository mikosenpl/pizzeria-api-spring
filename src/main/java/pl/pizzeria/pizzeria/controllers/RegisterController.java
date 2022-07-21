package pl.pizzeria.pizzeria.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.pizzeria.pizzeria.models.Role;
import pl.pizzeria.pizzeria.models.User;
import pl.pizzeria.pizzeria.requests.request.NewUserRequest;
import pl.pizzeria.pizzeria.services.RoleService;
import pl.pizzeria.pizzeria.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegisterController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody NewUserRequest request) {

        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPassword(request.getPassword());
            user.setId(UUID.randomUUID());

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            String[] roles = request.getRoles();
            for (String r : roles) {
                Optional<Role> role = roleService.findByName(r);
                if (role.isPresent()) {
                    user.getRoles().add(role.get());
                } else {
                    HashMap<String, String> response = new HashMap<>();
                    response.put("message", "Nie można odnaleźć roli: " + r);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }

            User userSave = userService.save(user);
            return new ResponseEntity<>(userSave, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Problem z dodaniem użytkownika ");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
