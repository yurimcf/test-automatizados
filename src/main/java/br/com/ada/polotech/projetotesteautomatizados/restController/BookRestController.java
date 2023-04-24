package br.com.ada.polotech.projetotesteautomatizados.restController;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import br.com.ada.polotech.projetotesteautomatizados.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookRestController {
    private final BookService service;

    @GetMapping
    public Book getExemplo() {
        return this.service.getExemplo();
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        return this.service.addBook(book);
    }

    //mudar para findbyid
    @GetMapping(value = "/{name}")
    public Book findByName(String name) {
        return this.service.findBookByName(name);
    }

    @PutMapping(value = "/{name}")
    public Book update(@PathVariable("name") String name,
                       @RequestBody Book book) {
        book.setName(name);
        return this.service.updateBook(book);
    }

    @DeleteMapping(value = "/{name}")
    public Book delete(@PathVariable("name") String name) {
        return this.service.deleteBookByName(name);
    }

    //fazzer o list depois
}
