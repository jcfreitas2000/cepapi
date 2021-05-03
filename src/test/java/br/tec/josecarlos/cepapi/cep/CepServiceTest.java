package br.tec.josecarlos.cepapi.cep;

import br.tec.josecarlos.cepapi.builder.CepBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = CepService.class)
class CepServiceTest {

    @Autowired
    private CepService cepService;

    @MockBean
    private CepRepository cepRepository;

    @Test
    public void getAllPossibleCeps_shouldFastFailed_whenReceiveInvalidCep() {
        assertThrows(ResponseStatusException.class, () -> cepService.getAllPossibleCeps(null));
        assertThrows(ResponseStatusException.class, () -> cepService.getAllPossibleCeps(""));
        assertThrows(ResponseStatusException.class, () -> cepService.getAllPossibleCeps("123"));
        assertThrows(ResponseStatusException.class, () -> cepService.getAllPossibleCeps("1234567"));
        assertThrows(ResponseStatusException.class, () -> cepService.getAllPossibleCeps("123456789"));
    }

    @Test
    public void getAllPossibleCeps_shouldReturnAllPossibleCeps_whenReceiveValidCep() {
        Set<String> allPossibleCeps = cepService.getAllPossibleCeps("12345678");

        assertEquals(8, allPossibleCeps.size());
        assertTrue(allPossibleCeps.contains("12345678"));
        assertTrue(allPossibleCeps.contains("12345670"));
        assertTrue(allPossibleCeps.contains("12345600"));
        assertTrue(allPossibleCeps.contains("12345000"));
        assertTrue(allPossibleCeps.contains("12340000"));
        assertTrue(allPossibleCeps.contains("12300000"));
        assertTrue(allPossibleCeps.contains("12000000"));
        assertTrue(allPossibleCeps.contains("10000000"));
    }

    @Test
    public void getAllPossibleCeps_shouldFastFailed_whenInvalidCep() {
        assertThrows(ResponseStatusException.class, () -> cepService.find(null));
        assertThrows(ResponseStatusException.class, () -> cepService.find(""));
        assertThrows(ResponseStatusException.class, () -> cepService.find("123"));
        assertThrows(ResponseStatusException.class, () -> cepService.find("1234567"));
        assertThrows(ResponseStatusException.class, () -> cepService.find("123456789"));
    }

    @Test
    public void find_shouldThrowResponseStatusException_whenNotFoundCep() {
        assertThrows(ResponseStatusException.class, () -> cepService.find("99999999"));
    }

    @Test
    public void find_shouldReturnRightCep() {
        Cep genericCep = CepBuilder.createGeneric();
        genericCep.setCep("12345678");
        Mockito.when(cepRepository.findFirstByCepInOrderByCepDesc(any())).thenReturn(Optional.of(genericCep));

        assertEquals(genericCep.getCep(), cepService.find(genericCep.getCep()).getCep());
    }
}
