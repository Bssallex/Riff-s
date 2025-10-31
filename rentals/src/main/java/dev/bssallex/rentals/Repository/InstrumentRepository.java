package dev.bssallex.rentals.Repository;

import dev.bssallex.rentals.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
