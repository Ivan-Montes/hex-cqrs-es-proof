package dev.ime.application.handler;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.FlightCreatedEvent;
import dev.ime.application.usecase.CreateFlightCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.FlightNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class CreateFlightCommandHandler implements CommandHandler {

	private final FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;	
	
	public CreateFlightCommandHandler(FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort) {
		super();
		this.flightNoSqlWriteRepositoryPort = flightNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
	}

	@Override
	public void handle(Command command) {
		
		if ( command instanceof CreateFlightCommand createFlightCommand) {
			
			FlightCreatedEvent event = new FlightCreatedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN),
					createFlightCommand.flightId(),
					createFlightCommand.origin(),
					createFlightCommand.destiny()
					);
			
			flightNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}

}
