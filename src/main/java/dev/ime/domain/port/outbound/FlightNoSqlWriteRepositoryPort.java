package dev.ime.domain.port.outbound;

import dev.ime.domain.event.Event;

public interface FlightNoSqlWriteRepositoryPort {

	void save(Event event);
	
}
