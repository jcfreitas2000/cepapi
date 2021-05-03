package br.tec.josecarlos.cepapi.cep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public Cep find(String cep) {
        return cepRepository
                .findFirstByCepInOrderByCepDesc(getAllPossibleCeps(cep))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("This CEP %s was not found", cep)));
    }

    public Set<String> getAllPossibleCeps(String cep) {
        isValidCep(cep);
        Set<String> possibleCeps = new HashSet<>();

        for (int i = cep.length(); i > 0; i--) {
            possibleCeps.add(cepWithZerosRightPad(cep.substring(0, i)));
        }

        return possibleCeps;
    }

    private String cepWithZerosRightPad(String cep) {
        return String.format("%-8s", cep)
                .replaceAll("\\s", "0");
    }

    private void isValidCep(String cep) {
        if (cep == null || !cep.matches("^\\d{8}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cep is not valid");
        }
    }
}
