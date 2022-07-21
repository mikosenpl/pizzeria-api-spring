package pl.pizzeria.pizzeria.requests.request;

import lombok.Data;
import pl.pizzeria.pizzeria.models.Client;
import pl.pizzeria.pizzeria.models.Pizza;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class NewOrderRequest
{

    @NotNull
    @NotBlank
    @Size(min=1, max= 20)
    private Double totalPrice;

    @NotBlank
    @Size(min=1, max= 200)
    private String description;

    @FutureOrPresent
    private LocalDate orderTime;

    @NotNull
    private UUID client;

    @NotNull
    private String[] orderPizzas;
}
