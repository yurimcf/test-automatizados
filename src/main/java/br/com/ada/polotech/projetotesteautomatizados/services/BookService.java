package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.exceptions.NegativeAmountException;
import br.com.ada.polotech.projetotesteautomatizados.exceptions.NullIdException;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book getExemplo() {
        return Book.builder()
                .name("As Crônicas de Nárnia")
                .edition("HarperCollins Paperback Box Set")
                .author("C.S. Lewis")
                .price(BigDecimal.valueOf(42.41))
                .amount(1)
                .build();
    }

    public Book addBook(Book book) {
        if (book.getName() == null) throw new NullIdException();
        if (book.getAmount() < 0) throw new NegativeAmountException();
        if (book.getPrice().compareTo(BigDecimal.ZERO) <0) throw new NegativeAmountException();
        return this.repository.save(book);
    }

    public Book findBookByName(String name) {
        if (name == null) {
            throw new NullIdException();
        }
        return this.repository.findById(name).get();
    }

    public Book updateBook(Book book) {
        if (book.getName() == null) throw new NullIdException();
        if (book.getAmount() < 0) throw new NegativeAmountException();
        if (book.getPrice().compareTo(BigDecimal.ZERO) <0) throw new NegativeAmountException();
        return this.repository.save(book);
    }

    //mudar para optional
    public Book deleteBookByName(String name) {
        Book deleted = findBookByName(name);
        this.repository.deleteById(name);
        return deleted;
    }

    public List<Book> bookList(List<String> nameBooks) {
        return this.repository.findAllById(nameBooks);
    }
}
