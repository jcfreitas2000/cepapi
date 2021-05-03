package br.tec.josecarlos.cepapi.cep;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CepFullContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void find_shouldReturnCep_whenValidCep() throws Exception {
        final String cep = "12345678";

        mockMvc.perform(get(String.format("/cep/%s", cep)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cep", is(cep)));
    }

    @Test
    public void find_shouldReturnFirstCepWithPaddingZeros_whenValidCep() throws Exception {
        final String cep = "12349678";

        mockMvc.perform(get(String.format("/cep/%s", cep)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cep", is("12340000")));
    }

    @Test
    public void find_shouldReturn404_whenNotFoundCep() throws Exception {
        final String cep = "01234567";

        mockMvc.perform(get(String.format("/cep/%s", cep)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void find_shouldReturn400_whenInvalidCepContainingNonNumberCharacter() throws Exception {
        final String cep = "invalcep";

        mockMvc.perform(get(String.format("/cep/%s", cep)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void find_shouldReturn400_whenInvalidCep() throws Exception {
        final String cep = "1233";

        mockMvc.perform(get(String.format("/cep/%s", cep)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void find_shouldReturn404_whenNullCep() throws Exception {
        mockMvc.perform(get("/cep/"))
                .andExpect(status().isNotFound());
    }
}
