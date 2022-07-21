package pl.pizzeria.pizzeria.requests.request;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.beans.Transient;

@Data
public class NewClientRequest {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @Email(message = "Email")
    private String email;

    @NotBlank
    @Size(min= 8, max = 15)
    private String phone;

    @NotBlank
    @Size(min = 10, max = 100)
    private String address;
}
