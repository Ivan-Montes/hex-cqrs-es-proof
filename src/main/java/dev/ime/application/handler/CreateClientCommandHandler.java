package dev.ime.application.handler;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.ClientCreatedEvent;
import dev.ime.application.usecase.CreateClientCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.ClientNoSqlWriteRepositoryPort;
import dev.ime.domain.port.outbound.DatabaseSequencePort;
import dev.ime.domain.port.outbound.PublisherPort;

@Component
public class CreateClientCommandHandler implements CommandHandler {

	private final ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort;
	private final DatabaseSequencePort databaseSequencePort;
	private final PublisherPort publisherPort;
	
	public CreateClientCommandHandler(ClientNoSqlWriteRepositoryPort clientNoSqlWriteRepositoryPort,
			DatabaseSequencePort databaseSequencePort, PublisherPort publisherPort) {
		super();
		this.clientNoSqlWriteRepositoryPort = clientNoSqlWriteRepositoryPort;
		this.databaseSequencePort = databaseSequencePort;
		this.publisherPort = publisherPort;
	}

	@Override
	public void handle(Command command) {
		
		if ( command instanceof CreateClientCommand createClientCommand) {
			
			ClientCreatedEvent event = new ClientCreatedEvent(
					databaseSequencePort.generateSequence(ApplicationConstant.SEQ_GEN),
					createClientCommand.clientId(),
					createClientCommand.name());
			
			clientNoSqlWriteRepositoryPort.save(event);
			publisherPort.publishEvent(event);
			
		} else {
			throw new IllegalArgumentException(ApplicationConstant.MSG_ILLEGAL_COMMAND);
		}
		
	}

}
