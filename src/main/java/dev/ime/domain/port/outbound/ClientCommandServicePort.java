package dev.ime.domain.port.outbound;

import java.util.UUID;


public interface ClientCommandServicePort<T> {

	void create(T dto);
	void update(UUID id, T dto);
	void deleteById(UUID id);
	
}
