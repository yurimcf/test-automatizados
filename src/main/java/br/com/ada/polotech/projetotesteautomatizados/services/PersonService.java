package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.exceptions.InvalidEmailException;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    private final EmailValidator emailValidator;


    public Person getExemplo() {
        return Person.builder() //pra isso é preciso ter um @Builder nas @Entitys
                .email("yurimcf@gmail.com")
                .name("Yuri")
                .phone("11995098415")
                .birthDate(LocalDate.of(1998, 10, 31))
                .cash(BigDecimal.valueOf(100))
                .build();
    }

    public Person addPerson(Person person) {
        if (!emailValidator.validate(person.getEmail())) {
            throw new InvalidEmailException();
        }
        return this.repository.save(person);
    }

    public Person findPersonByEmail(String email) {
        if (!emailValidator.validate(email)) {
            throw new InvalidEmailException();
        }
//        return this.repository.findById(email).orElseThrow(RuntimeException::new);
        return this.repository.findById(email).get();
    }

    public Person updatePerson(Person person) {
        if (!emailValidator.validate(person.getEmail())) {
            throw new InvalidEmailException();
        }
        return this.repository.save(person);
    }


}
