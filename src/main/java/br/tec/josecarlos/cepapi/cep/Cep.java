package br.tec.josecarlos.cepapi.cep;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Cep {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String cep;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
}
