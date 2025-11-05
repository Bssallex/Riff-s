package dev.bssallex.rentals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_RENTALS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    String tag;

    LocalDate dateRentals;

    LocalDate dateDevolution;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToMany
    @JoinTable(name = "TB_RENTALS_INSTRUMENT",
            joinColumns = @JoinColumn(name = "rentals_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private List<Instrument> instruments;
}
