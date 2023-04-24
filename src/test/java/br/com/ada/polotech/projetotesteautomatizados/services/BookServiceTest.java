package br.com.ada.polotech.projetotesteautomatizados.services;

import br.com.ada.polotech.projetotesteautomatizados.exceptions.InvalidEmailException;
import br.com.ada.polotech.projetotesteautomatizados.exceptions.NegativeAmountException;
import br.com.ada.polotech.projetotesteautomatizados.exceptions.NullIdException;
import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import br.com.ada.polotech.projetotesteautomatizados.model.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class BookServiceTest {
    protected final BookRepository repository = Mockito.mock(BookRepository.class);
    protected final BookService service = new BookService(repository);


    @Test
    void testGetExemplo() {
        Book book = Book.builder()
                .name("As Crônicas de Nárnia")
                .edition("HarperCollins Paperback Box Set")
                .author("C.S. Lewis")
                .price(BigDecimal.valueOf(42.41))
                .amount(1)
                .build();
        Book result = service.getExemplo();

        assertEquals(book.equals(result), result.equals(book));
    }

    @Test
    void testAddBook() {
        Book book = new Book("Avatar", "testEdition", "testAuthor",
                BigDecimal.ONE, 1);
        when(repository.save(any(Book.class))).thenReturn(book);

        Book result = service.addBook(book);

        assertEquals(book, result);
    }

    @Test
    void testAddBookNameNull() {
        Book book = new Book(null, "testEdition", "testAuthor",
                BigDecimal.ONE, 1);

        assertThrows(NullIdException.class, () -> {
            service.addBook(book);
        }, "Este campo não pode ser nulo");
    }

    @Test
    void testAddBookWithAmountNegative() {
        Book book = new Book("Avatar", "testEdition", "testAuthor",
                BigDecimal.ONE, -10);
        when(repository.save(any(Book.class))).thenReturn(book);

        assertThrows(NegativeAmountException.class, () -> service.addBook(book), "Quantidade não pode ser negativa");
    }

    @Test
    void testAddBookWithPriceNegative() {
        Book book = new Book("Avatar", "testEdition", "testAuthor",
                BigDecimal.valueOf(-10), 1);
        when(repository.save(any(Book.class))).thenReturn(book);

        assertThrows(NegativeAmountException.class, () -> service.addBook(book), "Quantidade não pode ser negativa");
    }

    @Test
    void testFindBookByName() {
        Book book = service.getExemplo();
        String name = book.getName();
        when(repository.save(any(Book.class))).thenReturn(book);
        when(repository.findById(name)).thenReturn(Optional.of(book));

        Book result = service.findBookByName(name);

        assertEquals(book, result);
    }

    @Test
    void testFindBookWithNameNull() {
        Book book = service.getExemplo();
        String name = null;
        when(repository.save(any(Book.class))).thenReturn(book);
        when(repository.findById(name)).thenReturn(Optional.of(book));


        assertThrows(NullIdException.class, () -> service.findBookByName(name), "Este campo não pode ser nulo");
    }

//    @Test
//    void tesFindBookWithNameWrong() {
//        Book book = service.getExemplo();
//        String name = "Nome errado";
//        when(repository.save(any(Book.class))).thenReturn(book);
//        when(repository.findById(name)).thenThrow().thenThrow(Exception.class);
//
//        Book result = service.findBookByName(name);
//
//        assertEquals(book, result);
//    }

    @Test
    void testDeleteBookByName() {
        Book forDelete = service.getExemplo();
        String nameBook = forDelete.getName();

        when(repository.save(any(Book.class))).thenReturn(forDelete);
        when(repository.findById(nameBook)).thenReturn(Optional.of(forDelete));

        Book result = service.deleteBookByName(nameBook);

        assertEquals(result, forDelete);
        verify(repository, times(1)).deleteById(nameBook);
    }

//    @Test
//    void testDeleteBookWithNameWrong() {
//        Book forDelete = service.getExemplo();
//        String nameBook = forDelete.getName();
//
//        when(repository.save(any(Book.class))).thenReturn(forDelete);
//        when(repository.findById(nameBook)).thenReturn(Optional.of(forDelete));
//
//        Book result = service.deleteBookByName(nameBook);
//
//        assertEquals(result, forDelete);
//        verify(repository,times(1)).deleteById(nameBook);
//    }

    @Test
    public void testUpdateBook() throws InvalidEmailException {
        Book testBook = service.getExemplo();
        Integer newAmount = 10;
        Book updatedBook = testBook;
        updatedBook.setAmount(newAmount);

        when(repository.save(any(Book.class))).thenReturn(testBook);

        when(repository.findById(testBook.getName())).thenReturn(Optional.of(testBook));
        when(repository.save(updatedBook)).thenReturn(updatedBook);

        Book result = service.updateBook(updatedBook);

        assertEquals(updatedBook, result);
        assertEquals(testBook.getName(), result.getName());
        assertEquals(testBook.getAmount(), result.getAmount());
    }

    @Test
    public void testUpdateBookWithNameNull() throws InvalidEmailException {
        Book testBook = service.getExemplo();
        Book updatedBook = testBook;
        updatedBook.setName(null);

        when(repository.save(any(Book.class))).thenReturn(testBook);

        when(repository.findById(testBook.getName())).thenReturn(Optional.of(testBook));


        assertThrows(NullIdException.class, () -> {
            service.updateBook(updatedBook);
        }, "Este campo não pode ser nulo");
    }

    @Test
    public void testUpdateBookWithAmountNegative() throws InvalidEmailException {
        Book testBook = service.getExemplo();
        Book updatedBook = testBook;
        updatedBook.setAmount(-10);

        when(repository.save(any(Book.class))).thenReturn(testBook);
        when(repository.findById(testBook.getName())).thenReturn(Optional.of(testBook));


        assertThrows(NegativeAmountException.class, () -> {
            service.updateBook(updatedBook);
        }, "Quantidade não pode ser negativa");
    }

    //    assertThrows(NegativeAmountException.class, () -> service.addBook(book), "Quantidade não pode ser negativa");
    @Test
    public void testUpdateBookWithPriceNegative() throws InvalidEmailException {
        Book testBook = service.getExemplo();
        Book updatedBook = testBook;
        updatedBook.setPrice(BigDecimal.valueOf(-10));

        when(repository.save(any(Book.class))).thenReturn(testBook);
        when(repository.findById(testBook.getName())).thenReturn(Optional.of(testBook));


        assertThrows(NegativeAmountException.class,
                () -> service.addBook(testBook),
                "Quantidade não pode ser negativa");
    }

    @Test
    void testListBooks() {
        List<String> nameBooks = new ArrayList<>();
        nameBooks.add("As Crônicas de Nárnia");
        nameBooks.add("O Senhor dos Anéis");

        Book book1 = Book.builder()
                .name("As Crônicas de Nárnia")
                .edition("HarperCollins Paperback Box Set")
                .author("C.S. Lewis")
                .price(BigDecimal.valueOf(42.41))
                .amount(1)
                .build();
        Book book2 = Book.builder()
                .name("O Senhor dos Anéis")
                .edition("Boxed Set")
                .author("J.R.R. Tolkien")
                .price(BigDecimal.valueOf(85.50))
                .amount(3)
                .build();
        List<Book> expectedList = new ArrayList<>();
        expectedList.add(book1);
        expectedList.add(book2);
        when(service.addBook(book1)).thenReturn(book1);
        when(service.addBook(book2)).thenReturn(book2);

        when(repository.findAllById(nameBooks)).thenReturn(expectedList);

        List<Book> actualList = service.bookList(nameBooks);

        assertEquals(expectedList, actualList);
    }
}