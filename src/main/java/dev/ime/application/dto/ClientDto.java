package dev.ime.application.dto;

import java.util.UUID;

import dev.ime.application.config.ApplicationConstant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ClientDto(
		UUID clientId,
		@NotEmpty @Size( min = 1, max = 100 ) String name
		) {
	
	public ClientDto() {
		this(UUID.fromString("00000000-0000-0000-0000-000000000000"), ApplicationConstant.NODATA);
	}
	
}
