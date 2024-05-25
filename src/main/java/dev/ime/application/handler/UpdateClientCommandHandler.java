package dev.ime.application.handler;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.ClientUpdatedEvent;
import dev.ime.application.exception.ResourceNotFoundException;
import dev.ime.application.usecase.UpdateClientCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.ClientNoSqlReadRepositoryPort;
import dev.ime.domain.port.outbound.ClientNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class UpdateClientCommandHandler implements CommandHandler{

	private final ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;
	private final ClientNoSqlReadRepositoryPort clientNoSqlReadRepositoryPort;

	public UpdateClientCommandHandler(ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort, ClientNoSqlReadRepositoryPort clientNoSqlReadRepositoryPort) {
		super();
		this.clientNoSqlWriteRepositoryPort = clientNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
		this.clientNoSqlReadRepositoryPort = clientNoSqlReadRepositoryPort;
	}

	@Override
	public void handle(Command command) {
		
		if ( command instanceof UpdateClientCommand updateClientCommand) {

			UUID clientId = updateClientCommand.clientId();
			
			if ( clientNoSqlReadRepositoryPort.findById(clientId).isEmpty() ) {
 
				throw new ResourceNotFoundException(Map.of(ApplicationConstant.CLIENTID,String.valueOf(clientId)));
			}
			
			ClientUpdatedEvent event = new ClientUpdatedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN),
					clientId,
					updateClientCommand.name()
					);
			
			clientNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}		
		
	}

}
