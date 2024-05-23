package dev.ime.domain.port.outbound;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.ime.domain.model.Flight;

public interface FlightQueryServicePort {

	List<Flight>getAll();
	Optional<Flight>getById(UUID id);
	
}
