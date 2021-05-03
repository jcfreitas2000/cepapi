package br.tec.josecarlos.cepapi.cep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CepResponse {

    private final String cep;
    @JsonProperty("rua")
    private final String street;
    @JsonProperty("bairro")
    private final String neighborhood;
    @JsonProperty("cidade")
    private final String city;
    @JsonProperty("estado")
    private final String state;

    public CepResponse(Cep cep) {
        this.cep = cep.getCep();
        this.street = cep.getStreet();
        this.neighborhood = cep.getNeighborhood();
        this.city = cep.getCity();
        this.state = cep.getState();
    }
}
