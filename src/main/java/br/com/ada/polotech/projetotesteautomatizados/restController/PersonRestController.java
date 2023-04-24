package br.com.ada.polotech.projetotesteautomatizados.restController;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor // Também pode ser usado assim
@RequestMapping("/person")
public class PersonRestController {
    private PersonService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person getExemplo() {
        return this.service.getExemplo();
    }


    @PostMapping
    public Person add(@RequestBody Person person) {
        return this.service.addPerson(person);
    }

    @GetMapping(value = "/{email}")
    public Person findByEmail(@PathVariable("email") String email) {
        return this.service.findPersonByEmail(email);
    }

    @PutMapping("/{email}")
    public Person update(@PathVariable("email") String email,
                         @RequestBody Person person) {
        person.setEmail(email);
        return this.service.updatePerson(person);
    }
}
