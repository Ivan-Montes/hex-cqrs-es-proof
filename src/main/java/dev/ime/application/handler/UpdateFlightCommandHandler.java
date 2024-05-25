package dev.ime.application.handler;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.FlightUpdatedEvent;
import dev.ime.application.exception.ResourceNotFoundException;
import dev.ime.application.usecase.UpdateFlightCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.FlightNoSqlReadRepositoryPort;
import dev.ime.domain.port.outbound.FlightNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class UpdateFlightCommandHandler implements CommandHandler{

	private final FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort;	
	private final FlightNoSqlReadRepositoryPort flightNoSqlReadRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;	
	
	public UpdateFlightCommandHandler(FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort, FlightNoSqlReadRepositoryPort flightNoSqlReadRepositoryPort) {
		super();
		this.flightNoSqlWriteRepositoryPort = flightNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
		this.flightNoSqlReadRepositoryPort = flightNoSqlReadRepositoryPort;
	}

	@Override
	public void handle(Command command) {
		
		if ( command instanceof UpdateFlightCommand updateFlightCommand ) {

			UUID flightId = updateFlightCommand.flightId();
			
			if ( flightNoSqlReadRepositoryPort.findById(flightId).isEmpty() ) {
				
				throw new ResourceNotFoundException(Map.of(ApplicationConstant.FLIGHTID,String.valueOf(flightId)));
			}
			
			FlightUpdatedEvent event = new FlightUpdatedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN),
					flightId,
					updateFlightCommand.origin(),
					updateFlightCommand.destiny()
					);
			
			flightNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}

}
