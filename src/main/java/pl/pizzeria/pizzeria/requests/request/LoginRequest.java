package pl.pizzeria.pizzeria.requests.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
