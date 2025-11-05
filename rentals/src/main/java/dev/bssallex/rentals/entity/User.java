package dev.bssallex.rentals.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "users")
    private List<Rentals> rentals; // Um usuário pode ter vários aluguéis
}
