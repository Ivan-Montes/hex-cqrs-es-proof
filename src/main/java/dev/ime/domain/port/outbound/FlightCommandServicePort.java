package dev.ime.domain.port.outbound;

import java.util.UUID;


public interface FlightCommandServicePort<T> {
	
	void create(T dto);
	void update(UUID id, T dto);
	void deleteById(UUID id);
	void addClientInFlight(UUID flightId, UUID clientId);
	void removeClientInFlight(UUID flightId, UUID clientId);
	
}
