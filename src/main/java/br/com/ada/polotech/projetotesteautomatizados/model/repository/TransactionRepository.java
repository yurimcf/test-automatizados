package br.com.ada.polotech.projetotesteautomatizados.model.repository;

import br.com.ada.polotech.projetotesteautomatizados.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
