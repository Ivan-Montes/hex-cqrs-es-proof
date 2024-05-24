package dev.ime.application.usecase;

import java.util.UUID;

import dev.ime.domain.command.Command;

public record UpdateFlightCommand(
		UUID flightId,	
		String origin,	
		String destiny
		) implements Command {

}
