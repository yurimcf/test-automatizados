package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.exceptions.InvalidEmailException;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersonServiceTest {
    protected final PersonRepository repository = Mockito.mock(PersonRepository.class);
    protected final EmailValidator emailValidator = Mockito.mock(EmailValidator.class);
    protected final PersonService service = new PersonService(repository, emailValidator);

    static Person person = new Person("yurimcf@gmail.com",
            "Yuri",
            LocalDate.of(1998, 10, 31),
            "11995098415",
            BigDecimal.valueOf(100));

    @Test
    void testGetExemplo() {
        Person personExemplo = Person.builder() //pra isso é preciso ter um @Builder nas @Entitys
                .email("yurimcf@gmail.com")
                .name("Yuri")
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();

        Person result = service.getExemplo();
        assertEquals(personExemplo.getEmail(),result.getEmail());
        assertEquals(personExemplo.getName(),result.getName());
        assertEquals(personExemplo.getPhone(),result.getPhone());
        assertEquals(personExemplo.getBirthDate(),result.getBirthDate());
        assertEquals(personExemplo.getCash(),result.getCash());
    }


    @Test
    void testAddPersonWithValidEmail() throws InvalidEmailException {
        when(emailValidator.validate(person.getEmail())).thenReturn(true);
        when(repository.save(any(Person.class))).thenReturn(person);

        Person savedPerson = service.addPerson(person);
        assertEquals(person, savedPerson);
    }

    @Test
    void testAddPersonWithInvalidEmail() throws InvalidEmailException {
        when(emailValidator.validate(person.getEmail())).thenReturn(false);
        when(repository.save(any(Person.class))).thenReturn(person);

        assertThrows(InvalidEmailException.class,
                () -> service.addPerson(person),
                "Email inválido, tente novamente");
    }

    @Test
    void testAddPersonWithEmptyEmail() throws InvalidEmailException {
        person.setEmail(null);
        when(repository.save(any(Person.class))).thenReturn(person);

        assertThrows(InvalidEmailException.class,
                () -> service.addPerson(person),
                "Email inválido, tente novamente");
    }

//    void testAddPersonAlreadyExists()

    @Test
    void testFindPersonByEmailWithInvalidEmail() {
        String email = "yurimcf@.com";
        person.setEmail(email);
        when(emailValidator.validate(person.getEmail())).thenReturn(false);
        when(repository.save(any(Person.class))).thenReturn(person);

        assertThrows(InvalidEmailException.class,
                () -> service.addPerson(person),
                "Email inválido, tente novamente");
    }

    @Test
    void testFindPersonByEmailWithValidEmail() {
        String email = person.getEmail();
        when(emailValidator.validate(person.getEmail())).thenReturn(true);
        when(repository.save(any(Person.class))).thenReturn(person);
        when(repository.findById(email)).thenReturn(Optional.of(person));

        Person findPerson = service.findPersonByEmail(email);
        assertEquals(person, findPerson);
    }

    @Test
    public void testUpdatePersonWithValidEmail() throws InvalidEmailException {
        String email = person.getEmail();
        String name = "Yuri Mathaus";
        Person updatedPerson = person;
        updatedPerson.setName(name);

        when(emailValidator.validate(email)).thenReturn(true);
        when(repository.save(any(Person.class))).thenReturn(person);

        when(repository.findById(person.getEmail())).thenReturn(Optional.of(person));
        when(repository.save(updatedPerson)).thenReturn(person);

        Person result = service.updatePerson(updatedPerson);

        assertEquals(updatedPerson, result);
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getEmail(), result.getEmail());
    }


}