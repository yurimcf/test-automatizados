package br.com.ada.polotech.projetotesteautomatizados.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person implements Serializable {
    @Id
    private String email;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private BigDecimal cash;


}
