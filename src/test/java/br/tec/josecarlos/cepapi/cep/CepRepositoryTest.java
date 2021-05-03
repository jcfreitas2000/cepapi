package br.tec.josecarlos.cepapi.cep;

import br.tec.josecarlos.cepapi.builder.CepBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CepRepositoryTest {

    @Autowired
    private CepRepository cepRepository;

    @Test
    public void save_shouldCreateAnCep_whenCalledWithValidCep() {
        Cep genericCep = CepBuilder.createGeneric();

        cepRepository.save(genericCep);
        Optional<Cep> cepOptional = cepRepository.findById(genericCep.getId());

        assertDoesNotThrow((ThrowingSupplier<Cep>) cepOptional::orElseThrow);
        assertEquals(genericCep, cepOptional.orElseThrow());
        assertEquals(genericCep.getCep(), cepOptional.orElseThrow().getCep());
        assertEquals(9, cepRepository.count());
    }

    @Test
    public void shouldHaveInitialCepData_whenStartApplication() {
        assertEquals(8, cepRepository.count());
    }
}
