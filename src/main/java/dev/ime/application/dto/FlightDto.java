package dev.ime.application.dto;

import java.util.Set;
import java.util.UUID;

import dev.ime.application.config.ApplicationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FlightDto(
		UUID flightId,
		@NotBlank @Size( min = 1, max = 50 ) String origin,
		@NotBlank @Size( min = 1, max = 50 ) String destiny,
		Set<ClientDto> clientSet
		) {
	
	public FlightDto() {
		this(UUID.fromString("00000000-0000-0000-0000-000000000000"), ApplicationConstant.NODATA, "", null);
	}
	
}
