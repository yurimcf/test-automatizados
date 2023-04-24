package br.com.ada.polotech.projetotesteautomatizados.restController;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PersonService service;
    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUri = "/person";

    @Test
    void getExemplo() throws Exception {
        Person exemple = Person.builder()
                .email("yurimcf@gmail.com")
                .name("Yuri")
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();

        String response = mvc.perform(get(baseUri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(exemple.getName()))
                .andExpect(jsonPath("$.email").value(exemple.getEmail()))
                .andExpect(jsonPath("$.phone").value(exemple.getPhone()))
                .andExpect(jsonPath("$.birthDate").value(exemple.getBirthDate().toString()))
                .andExpect(jsonPath("$.cash").value(exemple.getCash().toString()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void testAddPersonValida() throws Exception {
        Person person = Person.builder()
                .email("yurimcf@gmail.com")
                .name("Yuri")
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();

        String requestBody = objectMapper.writeValueAsString(person);

        mvc.perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is("yurimcf@gmail.com")))
                .andExpect(jsonPath("$.name", is("Yuri")))
                .andExpect(jsonPath("$.phone", is("11995098415")))
                .andExpect(jsonPath("$.birthDate", is("1998-10-31")))
                .andExpect(jsonPath("$.cash", is(100)));

        Person addPerson = service.addPerson(person);
        assertEquals(addPerson.equals(person),person.equals(addPerson));
    }

    @Test
    void findByEmail() {
    }

    @Test
    public void testUpdatePerson() throws Exception {
        // Cria um objeto de Person para ser atualizado
        Person person = Person.builder()
                .email("yurimcf@gmail.com")
                .name("Yuri")
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();

        // Define o novo email para a atualização
        String newName = "Yuri Mathaus";
        person.setName(newName);

        // Define o retorno esperado do método updatePerson
        Person updatedPerson = Person.builder()
                .email(person.getEmail())
                .name(newName)
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();
        //isso aqui não ta bugando
//        when(service.updatePerson(person)).thenReturn(updatedPerson);

        // Executa a requisição PUT
        mvc.perform(put(baseUri+"/{email}", person.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.name", is("Yuri Mathaus")))
                .andExpect(jsonPath("$.phone", is("11995098415")))
                .andExpect(jsonPath("$.birthDate", is("1998-10-31")))
                .andExpect(jsonPath("$.cash", is(100)));
    }
}