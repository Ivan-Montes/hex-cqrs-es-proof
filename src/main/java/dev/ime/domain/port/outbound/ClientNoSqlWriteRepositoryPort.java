package dev.ime.domain.port.outbound;

import dev.ime.domain.event.Event;

public interface ClientNoSqlWriteRepositoryPort {
	
	void save(Event event);

}
