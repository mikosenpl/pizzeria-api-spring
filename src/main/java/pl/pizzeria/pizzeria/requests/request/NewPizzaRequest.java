package pl.pizzeria.pizzeria.requests.request;

import lombok.Data;
import pl.pizzeria.pizzeria.models.Order;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class NewPizzaRequest {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @PositiveOrZero
    private Double smallPrice;

    @NotNull
    @PositiveOrZero
    private Double bigPrice;

    @NotBlank
    @Size(min = 2, max = 200)
    private String description;

}
