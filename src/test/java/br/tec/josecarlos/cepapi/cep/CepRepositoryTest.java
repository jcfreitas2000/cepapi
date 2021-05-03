package br.tec.josecarlos.cepapi.cep;

import br.tec.josecarlos.cepapi.builder.CepBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void findAllByCepInOrderByCepDesc_shouldReturnAllCepsSortedDesc() {
        List<Cep> cepList = cepRepository.findAllByCepInOrderByCepDesc(
                Stream.of(
                        "12345678",
                        "12345670",
                        "12345600",
                        "12345000",
                        "12340000",
                        "12300000",
                        "12000000",
                        "10000000"
                ).collect(Collectors.toSet())
        );

        assertEquals(8, cepList.size());
        assertTrue(containsCep(cepList, "12345678"));
        assertTrue(containsCep(cepList, "12345670"));
        assertTrue(containsCep(cepList, "12345600"));
        assertTrue(containsCep(cepList, "12345000"));
        assertTrue(containsCep(cepList, "12340000"));
        assertTrue(containsCep(cepList, "12300000"));
        assertTrue(containsCep(cepList, "12000000"));
        assertTrue(containsCep(cepList, "10000000"));
    }

    @Test
    public void findFirstByCepInOrderByCepDesc_shouldReturnFirstCepSortedDesc() {
        Optional<Cep> cep = cepRepository.findFirstByCepInOrderByCepDesc(
                Stream.of(
                        "12345671",
                        "12345670",
                        "12345600",
                        "12345000",
                        "12340000",
                        "12300000",
                        "12000000",
                        "10000000"
                ).collect(Collectors.toSet())
        );

        assertEquals("12345670", cep.orElseThrow().getCep());
    }

    private boolean containsCep(List<Cep> cepList, final String cepNumber) {
        return cepList.stream().filter(cep -> cep.getCep().equals(cepNumber)).count() == 1;
    }
}
