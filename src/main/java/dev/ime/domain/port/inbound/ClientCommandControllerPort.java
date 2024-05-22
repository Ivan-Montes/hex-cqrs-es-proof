package dev.ime.domain.port.inbound;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

public interface ClientCommandControllerPort<T> {

	ResponseEntity<Boolean>create(T dto);
	ResponseEntity<Boolean>update(UUID id, T dto);
	ResponseEntity<Boolean>deleteById(UUID id);
	
}
