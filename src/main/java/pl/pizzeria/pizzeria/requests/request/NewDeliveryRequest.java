package pl.pizzeria.pizzeria.requests.request;

import lombok.Data;
import lombok.Getter;
import pl.pizzeria.pizzeria.models.Order;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class NewDeliveryRequest {

    @NotBlank
    @Size(min = 2, max = 10)
    private boolean deliveryStatus;

    @FutureOrPresent
    private LocalDate deliveryTime;

    @NotNull
    private UUID order;

}
