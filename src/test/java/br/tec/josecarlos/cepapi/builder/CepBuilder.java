package br.tec.josecarlos.cepapi.builder;

import br.tec.josecarlos.cepapi.cep.Cep;

public class CepBuilder {

    private final Cep cep = new Cep();

    public CepBuilder cep(String cep) {
        this.cep.setCep(cep);
        return this;
    }

    public CepBuilder street(String street) {
        this.cep.setStreet(street);
        return this;
    }

    public CepBuilder neighborhood(String neighborhood) {
        this.cep.setNeighborhood(neighborhood);
        return this;
    }

    public CepBuilder city(String city) {
        this.cep.setCity(city);
        return this;
    }

    public CepBuilder state(String state) {
        this.cep.setState(state);
        return this;
    }

    public Cep create() {
        return this.cep;
    }

    public static Cep createGeneric() {
        return new CepBuilder()
                .cep("11222333")
                .street("Another Street")
                .neighborhood("Another Neighborhood")
                .city("Another City")
                .state("Another State")
                .create();
    }
}
