package dev.ime.application.handler;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.ClientDeletedEvent;
import dev.ime.application.usecase.DeleteClientCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.ClientNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class DeleteClientCommandHandler implements CommandHandler{

	private final ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;
	
	public DeleteClientCommandHandler(ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort) {
		super();
		this.clientNoSqlWriteRepositoryPort = clientNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
	}
	
	@Override
	public void handle(Command command) {
		
		if ( command instanceof DeleteClientCommand deleteClientCommand) {
			
			ClientDeletedEvent event = new ClientDeletedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN),
					deleteClientCommand.clientId());
			
			clientNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}

}
