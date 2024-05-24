package dev.ime.application.handler;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.FlightDeletedEvent;
import dev.ime.application.usecase.DeleteFlightCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.FlightNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class DeleteFlightCommandHandler implements CommandHandler{

	private final FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;	
	
	public DeleteFlightCommandHandler(FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort) {
		super();
		this.flightNoSqlWriteRepositoryPort = flightNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
	}

	@Override
	public void handle(Command command) {
		
		if ( command instanceof DeleteFlightCommand deleteFlightCommand ) {
			
			FlightDeletedEvent event = new FlightDeletedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN), 
					deleteFlightCommand.flightId()
					);
			
			flightNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}
	
}
