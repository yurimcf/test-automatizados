package br.com.ada.polotech.projetotesteautomatizados.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "email")
    private Person person;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    private List<Book> bookList;

    private String status;


}
