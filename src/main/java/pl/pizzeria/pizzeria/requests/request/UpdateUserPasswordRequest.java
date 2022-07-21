package pl.pizzeria.pizzeria.requests.request;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class UpdateUserPasswordRequest {
    @NotNull
    @NotBlank
    @Size(min = 6, max = 100)
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    private UUID id;
}
