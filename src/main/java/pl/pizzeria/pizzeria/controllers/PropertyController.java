package pl.pizzeria.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PropertyController {

    public static String JWT_SECRET;

    @Value("${app.jwtSecret}")
    public void setNameStatic(String name) {
        PropertyController.JWT_SECRET = name;
    }
}
