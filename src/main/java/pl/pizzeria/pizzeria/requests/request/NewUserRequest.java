package pl.pizzeria.pizzeria.requests.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewUserRequest {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Email
    private String email;

    @NotNull
    private String[] roles;
}