package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.DTO.BookDTO;
import br.com.ada.polotech.projetotesteautomatizados.exceptions.InsufficientValueException;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Person;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Transaction;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final PersonService personService;
    private final BookService bookService;

    public Transaction createTransaction(String emailPerson, List<String> nameBook) {
        return this.repository.save(Transaction.builder()
                .person(this.personService.findPersonByEmail(emailPerson))
                .bookList(this.bookService.bookList(nameBook))
                .build());
    }

    public Transaction retrieveTransaction(Long id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Person checkout(Long id) {
        Transaction analysis = retrieveTransaction(id);
        Person purchaser = this.personService.findPersonByEmail(
                analysis.getPerson().getEmail());

        BigDecimal amount = totalPrice(analysis.getBookList());
        if (purchaser.getCash().compareTo(amount) < 0) {
            throw new InsufficientValueException();
        } else {
            BigDecimal updated = purchaser.getCash().subtract(amount);
            purchaser.setCash(updated);


        }
        return null;
    }

    private BigDecimal totalPrice(List<Book> bookList) {
        BigDecimal amount = BigDecimal.ONE;
        for (Book book : bookList) {
            amount = amount.add(book.getPrice());
        }
        return amount;
    }
}
