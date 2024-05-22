package dev.ime.domain.port.outbound;

import dev.ime.domain.event.Event;

public interface FlightProjectorPort {

	void create(Event event);
	void update(Event event);
	void deleteById(Event event);
	void addClientInFlight(Event event);
	void removeClientInFlight(Event event);
	
}
