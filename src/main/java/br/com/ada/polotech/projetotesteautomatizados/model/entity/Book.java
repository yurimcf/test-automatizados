package br.com.ada.polotech.projetotesteautomatizados.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book implements Serializable {
    @Id
    private String name;
    private String edition;
    private String author;
    private BigDecimal price;
    private Integer amount;

}
