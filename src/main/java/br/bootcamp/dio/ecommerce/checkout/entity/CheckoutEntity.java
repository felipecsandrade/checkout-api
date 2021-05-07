package br.bootcamp.dio.ecommerce.checkout.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status {
        CREATED,
        APPROVED
    }

}
