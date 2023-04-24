package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Transaction;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.TransactionRepository;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    private TransactionRepository repository = Mockito.mock(TransactionRepository.class);
    private PersonService personService = Mockito.mock(PersonService.class);
    private BookService bookService = Mockito.mock(BookService.class);
    private TransactionService service = new TransactionService(repository, personService,bookService);



}