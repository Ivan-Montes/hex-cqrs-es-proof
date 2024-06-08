package dev.ime.infrastructure.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ime.application.dto.FlightDto;
import dev.ime.domain.port.inbound.FlightCommandControllerPort;
import dev.ime.domain.port.outbound.FlightCommandServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flights")
@Tag( name="Flight", description="Flight Operations")
public class FlightCommandController implements FlightCommandControllerPort<FlightDto> {

	private final FlightCommandServicePort<FlightDto> flightCommandServicePort;
	
	public FlightCommandController(FlightCommandServicePort<FlightDto> flightCommandServicePort) {
		super();
		this.flightCommandServicePort = flightCommandServicePort;
	}

	@PostMapping
	@Override
	@Operation(summary="Create a new Flight", description="Create a new Flight, @return an object Response")
	public ResponseEntity<Boolean> create(@Valid @RequestBody FlightDto dto) {
		
		flightCommandServicePort.create(dto);
		
		return ResponseEntity.ok(true);
	}

	@PutMapping("/{id}")
	@Override
	@Operation(summary="Update fields in a Flight", description="Update fields in a Flight, @return an object Response")
	public ResponseEntity<Boolean> update(@PathVariable UUID id, @Valid @RequestBody FlightDto dto) {

		flightCommandServicePort.update(id, dto);
		
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/{id}")
	@Override
	@Operation(summary="Delete a Flight by its Id", description="Delete a Flight by its Id, @return an object Response")
	public ResponseEntity<Boolean> deleteById(@PathVariable UUID id) {

		flightCommandServicePort.deleteById(id);
		
		return ResponseEntity.ok(true);
	}

	@PutMapping("/{flightId}/{clientId}")
	@Override
	@Operation(summary="Add a Client in a Flight by their Id", description="Add a Client in a Flight by their Id, @return an object Response")
	public ResponseEntity<Boolean> addClientInFlight(@PathVariable UUID flightId, @PathVariable UUID clientId) {

		flightCommandServicePort.addClientInFlight(flightId, clientId);
		
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/{flightId}/{clientId}")
	@Override
	@Operation(summary="Remove a Client in a Flight by their Id", description="Remove a Client in a Flight by their Id, @return an object Response")
	public ResponseEntity<Boolean> removeClientInFlight(@PathVariable UUID flightId, @PathVariable UUID clientId) {

		flightCommandServicePort.removeClientInFlight(flightId, clientId);
		
		return ResponseEntity.ok(true);
	}

}
