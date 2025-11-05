package dev.bssallex.rentals.entity;

import dev.bssallex.rentals.enums.Available;
import dev.bssallex.rentals.enums.TypeInstrument;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_INSTRUMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TypeInstrument typeInstrument;

    @Enumerated(EnumType.STRING)
    private Available available;

    @ManyToMany(mappedBy = "instruments") // Muitos instrumentos podem estar em muitos aluguéis
    private List<Rentals> rentals;
}
