package dev.ime.application.dispatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.handler.AddClientInFlightCommadHandler;
import dev.ime.application.handler.CreateFlightCommandHandler;
import dev.ime.application.handler.UpdateFlightCommandHandler;
import dev.ime.application.handler.DeleteFlightCommandHandler;
import dev.ime.application.handler.RemoveClientInFlightCommadHandler;
import dev.ime.application.usecase.AddClientInFlightCommad;
import dev.ime.application.usecase.CreateFlightCommand;
import dev.ime.application.usecase.DeleteFlightCommand;
import dev.ime.application.usecase.RemoveClientInFlightCommad;
import dev.ime.application.usecase.UpdateFlightCommand;
import dev.ime.domain.command.Command;
import dev.ime.domain.command.CommandHandler;

@Component
public class FlightCommandDispatcher {

	private final Map<Class<? extends Command>, CommandHandler> commandHandlers = new HashMap<>();
	
	public FlightCommandDispatcher(CreateFlightCommandHandler createFlightCommandHandler, UpdateFlightCommandHandler updateFlightCommandHandler,
			DeleteFlightCommandHandler deleteFlightCommandHandler, AddClientInFlightCommadHandler addClientInFlightCommadHandler,
			RemoveClientInFlightCommadHandler removeClientInFlightCommadHandler) {
		super();
		commandHandlers.put(CreateFlightCommand.class, createFlightCommandHandler);
		commandHandlers.put(UpdateFlightCommand.class, updateFlightCommandHandler);
		commandHandlers.put(DeleteFlightCommand.class, deleteFlightCommandHandler);
		commandHandlers.put(AddClientInFlightCommad.class, addClientInFlightCommadHandler);
		commandHandlers.put(RemoveClientInFlightCommad.class, removeClientInFlightCommadHandler);
	}

	public CommandHandler getCommandHandler(Command command) {
		
		CommandHandler handler = commandHandlers.get(command.getClass());
		
		if (handler == null) {
			
			throw new IllegalArgumentException(ApplicationConstant.MSG_NO_HANDLER + command.getClass().getName());
		} 
		
		return handler;		
		
	}
	
}
