package br.com.ada.polotech.projetotesteautomatizados.restController;

import br.com.ada.polotech.projetotesteautomatizados.DTO.BookDTO;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Transaction;
import br.com.ada.polotech.projetotesteautomatizados.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionRestController {
    private final TransactionService service;

    @PostMapping(value = "/{email}")
    public Transaction create (@PathVariable("email") String email,
                               @RequestBody List<String> nameBook){
        return this.service.createTransaction(email,nameBook);
    }

    @GetMapping(value = "/status/{id}")
    public Transaction status (@PathVariable("id") Long id) {
        return this.service.retrieveTransaction(id);
    }

    @PutMapping(value = "/checkout/{id}")
    public Person checkout (@PathVariable("id") Long id){
        return this.service.checkout(id);
    }
}
