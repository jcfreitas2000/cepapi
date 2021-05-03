package br.tec.josecarlos.cepapi.cep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("{cep}")
    public CepResponse find(@PathVariable String cep) {
        return new CepResponse(cepService.find(cep));
    }
}
