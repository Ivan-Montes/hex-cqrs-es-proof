package dev.ime.application.handler;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.FlightClientRemovedEvent;
import dev.ime.application.exception.ResourceNotFoundException;
import dev.ime.application.usecase.RemoveClientInFlightCommad;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.ClientNoSqlReadRepositoryPort;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.FlightNoSqlReadRepositoryPort;
import dev.ime.domain.port.outbound.FlightNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class RemoveClientInFlightCommadHandler implements CommandHandler{

	private final FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;	
	private final ClientNoSqlReadRepositoryPort clientNoSqlReadRepositoryPort;		
	private final FlightNoSqlReadRepositoryPort flightNoSqlReadRepositoryPort;
	
	public RemoveClientInFlightCommadHandler(FlightNoSqlWriteRepositoryPort flightNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort, FlightNoSqlReadRepositoryPort flightNoSqlReadRepositoryPort, ClientNoSqlReadRepositoryPort clientNoSqlReadRepositoryPort) {
		super();
		this.flightNoSqlWriteRepositoryPort = flightNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
		this.flightNoSqlReadRepositoryPort = flightNoSqlReadRepositoryPort;
		this.clientNoSqlReadRepositoryPort = clientNoSqlReadRepositoryPort;
	}	

	@Override
	public void handle(Command command) {		
		
		if ( command instanceof RemoveClientInFlightCommad removeClientInFlightCommad) {

			final UUID flightId = removeClientInFlightCommad.flightId();
			validateFlightExists(flightId);
			
			final UUID clientId = removeClientInFlightCommad.clientId();
			validateClientExists(clientId);
			
			FlightClientRemovedEvent event = new FlightClientRemovedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN), 
					flightId,
					clientId
					);			
			
			flightNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		}else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}

	private void validateFlightExists(UUID flightId) {
		
		if ( flightNoSqlReadRepositoryPort.findById(flightId).isEmpty() ) {
			
			throw new ResourceNotFoundException(Map.of(ApplicationConstant.FLIGHTID,String.valueOf(flightId)));
		}
		
	}
	
	private void validateClientExists(UUID clientId) {
		
        if (clientNoSqlReadRepositoryPort.findById(clientId).isEmpty()) {
        	
            throw new ResourceNotFoundException(Map.of(ApplicationConstant.CLIENTID, String.valueOf(clientId)));
        }
        
    }
	
}
