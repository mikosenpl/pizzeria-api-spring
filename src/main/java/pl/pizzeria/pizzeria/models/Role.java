package pl.pizzeria.pizzeria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Role implements Persistable<UUID>
{
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;


    @Override
    public UUID getId() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return id == null;
    }
}
