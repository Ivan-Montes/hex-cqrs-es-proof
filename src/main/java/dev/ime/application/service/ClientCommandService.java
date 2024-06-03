package dev.ime.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.ime.application.dispatch.ClientCommandDispatcher;
import dev.ime.application.dto.ClientDto;
import dev.ime.application.usecase.CreateClientCommand;
import dev.ime.application.usecase.DeleteClientCommand;
import dev.ime.application.usecase.UpdateClientCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;
import dev.ime.domain.port.outbound.ClientCommandServicePort;

@Service
public class ClientCommandService implements ClientCommandServicePort<ClientDto>{

	private final ClientCommandDispatcher clientCommandDispatcher;	
	
	public ClientCommandService(ClientCommandDispatcher clientCommandDispatcher) {
		super();
		this.clientCommandDispatcher = clientCommandDispatcher;
	}

	@Override
	public void create(ClientDto dto) {
		
		CreateClientCommand createClientCommand = new CreateClientCommand(UUID.randomUUID(), dto.name());
		handleCommand(createClientCommand);
		
	}

	@Override
	public void update(UUID id, ClientDto dto) {
		
		UpdateClientCommand updateClientCommand = new UpdateClientCommand(id, dto.name());
		handleCommand(updateClientCommand);
		
	}

	@Override
	public void deleteById(UUID id) {
		
		DeleteClientCommand deleteClientCommand = new DeleteClientCommand(id);
		handleCommand(deleteClientCommand);
		
	}

	private void handleCommand(Command command) {
		
        CommandHandler handler = clientCommandDispatcher.getCommandHandler(command);
        handler.handle(command);
        
    }
	
}
