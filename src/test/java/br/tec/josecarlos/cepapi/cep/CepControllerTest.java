package br.tec.josecarlos.cepapi.cep;

import br.tec.josecarlos.cepapi.builder.CepBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CepController.class)
class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepService cepService;

    @Test
    public void find_shouldReturnCep() throws Exception {
        Cep genericCep = CepBuilder.createGeneric();
        when(cepService.find(anyString())).thenReturn(genericCep);

        mockMvc.perform(get(String.format("/cep/%s", genericCep.getCep())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cep", is(genericCep.getCep())))
                .andExpect(jsonPath("rua", is(genericCep.getStreet())))
                .andExpect(jsonPath("bairro", is(genericCep.getNeighborhood())))
                .andExpect(jsonPath("cidade", is(genericCep.getCity())))
                .andExpect(jsonPath("estado", is(genericCep.getState())));
    }

    @Test
    public void find_shouldReturn404_whenNotFoundCep() throws Exception {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        Cep genericCep = CepBuilder.createGeneric();
        when(cepService.find(anyString())).thenThrow(new ResponseStatusException(notFound));

        mockMvc.perform(get(String.format("/cep/%s", genericCep.getCep())))
                .andExpect(status().isNotFound());
    }
}
