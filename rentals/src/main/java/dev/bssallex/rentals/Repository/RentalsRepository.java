package dev.bssallex.rentals.Repository;

import dev.bssallex.rentals.entity.Rentals;
import dev.bssallex.rentals.enums.TypeInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, UUID> {

    List<Rentals> findByUsers_name(String name);
    Optional<Rentals> findByTag(String tag);
    long countByInstruments_TypeInstrument(TypeInstrument type);
}
