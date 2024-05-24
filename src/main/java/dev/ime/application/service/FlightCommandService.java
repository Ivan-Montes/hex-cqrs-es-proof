package dev.ime.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.ime.application.dispatch.FlightCommandDispatcher;
import dev.ime.application.dto.FlightDto;
import dev.ime.application.usecase.AddClientInFlightCommad;
import dev.ime.application.usecase.CreateFlightCommand;
import dev.ime.application.usecase.DeleteFlightCommand;
import dev.ime.application.usecase.RemoveClientInFlightCommad;
import dev.ime.application.usecase.UpdateFlightCommand;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.FlightCommandServicePort;

@Service
public class FlightCommandService implements FlightCommandServicePort<FlightDto>{

	private final FlightCommandDispatcher flightCommandDispatcher;	
	
	public FlightCommandService(FlightCommandDispatcher flightCommandDispatcher) {
		super();
		this.flightCommandDispatcher = flightCommandDispatcher;
	}

	@Override
	public void create(FlightDto dto) {
		
		CreateFlightCommand createFlightCommand = new CreateFlightCommand(UUID.randomUUID(), dto.origin(), dto.destiny());
		CommandHandler commandHandler = flightCommandDispatcher.getCommandHandler(createFlightCommand);
		commandHandler.handle(createFlightCommand);
		
	}

	@Override
	public void update(UUID id, FlightDto dto) {
		
		UpdateFlightCommand updateFlightCommand = new UpdateFlightCommand(id, dto.origin(), dto.destiny());
		CommandHandler commandHandler = flightCommandDispatcher.getCommandHandler(updateFlightCommand);
		commandHandler.handle(updateFlightCommand);
		
	}

	@Override
	public void deleteById(UUID id) {
		
		DeleteFlightCommand deleteFlightCommand = new DeleteFlightCommand(id);
		CommandHandler commandHandler = flightCommandDispatcher.getCommandHandler(deleteFlightCommand);
		commandHandler.handle(deleteFlightCommand);
		
	}

	@Override
	public void removeClientInFlight(UUID flightId, UUID clientId) {

		RemoveClientInFlightCommad removeClientInFlightCommad = new RemoveClientInFlightCommad(flightId, clientId);
		CommandHandler commandHandler = flightCommandDispatcher.getCommandHandler(removeClientInFlightCommad);
		commandHandler.handle(removeClientInFlightCommad);
		
	}

	@Override
	public void addClientInFlight(UUID flightId, UUID clientId) {
		
		AddClientInFlightCommad addClientInFlightCommad = new AddClientInFlightCommad(flightId, clientId);
		CommandHandler commandHandler = flightCommandDispatcher.getCommandHandler(addClientInFlightCommad);
		commandHandler.handle(addClientInFlightCommad);
		
	}

}
