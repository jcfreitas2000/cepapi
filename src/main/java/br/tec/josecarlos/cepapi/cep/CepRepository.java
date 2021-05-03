package br.tec.josecarlos.cepapi.cep;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CepRepository extends CrudRepository<Cep, UUID> {

    List<Cep> findAllByCepInOrderByCepDesc(Collection<String> ceps);

}
