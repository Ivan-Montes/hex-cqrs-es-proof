package dev.ime.domain.port.inbound;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

public interface ClientQueryControllerPort<T> {

	ResponseEntity<List<T>> getAll();
	ResponseEntity<T>getById(UUID id);
}
