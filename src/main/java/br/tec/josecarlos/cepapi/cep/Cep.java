package br.tec.josecarlos.cepapi.cep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(indexes = @Index(name = "cepIndex", columnList = "cep desc", unique = true))
public class Cep {

    @Id
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
}
