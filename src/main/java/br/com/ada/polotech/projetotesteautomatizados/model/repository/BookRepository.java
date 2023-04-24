package br.com.ada.polotech.projetotesteautomatizados.model.repository;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
