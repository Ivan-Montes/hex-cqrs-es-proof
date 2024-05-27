package dev.ime.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.ClientCreatedEvent;
import dev.ime.application.event.ClientDeletedEvent;
import dev.ime.application.event.ClientUpdatedEvent;
import dev.ime.domain.event.Event;
import dev.ime.domain.port.outbound.ClientProjectorPort;
import dev.ime.infrastructure.entity.ClientMongoEntity;
import dev.ime.infrastructure.repository.read.ClientNoSqlReadRepository;

@Repository
public class ClientProjectorAdapter implements ClientProjectorPort{

	private final ClientNoSqlReadRepository clientNoSqlReadRepository;
	private final Logger logger;
	
	public ClientProjectorAdapter(ClientNoSqlReadRepository clientNoSqlReadRepository,
			Logger logger) {
		super();
		this.clientNoSqlReadRepository = clientNoSqlReadRepository;
		this.logger = logger;
	}

	@Override
	public void create(Event event) {
		
		if ( event instanceof ClientCreatedEvent clientCreatedEvent) {
			
			ClientMongoEntity clientMongoEntity = buildClientEntity(clientCreatedEvent);
			
			saveClientEntity(clientMongoEntity);
			
			//logger.log(Level.INFO, "### [ClientProjectorAdapter] -> [ClientCreatedEvent] -> [ {0} ]", clientMongoEntity);
			logInfoAction("ClientCreatedEvent", clientMongoEntity.toString());
		}
		
	}

	@Override
	public void update(Event event) {

		if ( event instanceof ClientUpdatedEvent clientUpdatedEvent) {
			
			UUID id = clientUpdatedEvent.getClientId();			
			Optional<ClientMongoEntity>optClientFound = clientNoSqlReadRepository.findFirstByClientId(id);	
			
			if ( optClientFound.isEmpty() ) {
				
				logger.info( () -> "### "+ this.getClass().getSimpleName() +" -> [ClientUpdatedEvent] -> [ResourceNotFoundException] -> [ " + ApplicationConstant.CLIENTID + " : " + id + " ]");
				return;
			}

			ClientMongoEntity clientFound = optClientFound.get();
			clientFound.setName(clientUpdatedEvent.getName());			
			
			clientNoSqlReadRepository.save(clientFound);	
			
			logger.log(Level.INFO, "### [ClientProjectorAdapter] -> [ClientUpdatedEvent] -> [ {0} ]", clientFound);

		}	
		
	}

	@Override
	public void deleteById(Event event) {

		if ( event instanceof ClientDeletedEvent clientDeletedEvent) {
			
			UUID id = clientDeletedEvent.getClientId();
			Optional<ClientMongoEntity>optClientFound = clientNoSqlReadRepository.findFirstByClientId(id);	
			
			if ( optClientFound.isEmpty() ) {
				
				logger.info( () -> "### [ClientProjectorAdapter] -> [ClientDeletedEvent] -> [ResourceNotFoundException] -> [ " + ApplicationConstant.CLIENTID + " : " + id + " ]");
				return;
			}
			
			clientNoSqlReadRepository.delete(optClientFound.get());
			
			logger.log(Level.INFO, "### [ClientProjectorAdapter] -> [ClientDeletedEvent] -> [ " + ApplicationConstant.CLIENTID + " : {0} ]", (id != null? id:ApplicationConstant.UNKNOWDATA));

		}
		
	}

	private ClientMongoEntity buildClientEntity(ClientCreatedEvent clientCreatedEvent) {
		
	    ClientMongoEntity clientMongoEntity = new ClientMongoEntity();
	    clientMongoEntity.setClientId(clientCreatedEvent.getClientId());
	    clientMongoEntity.setName(clientCreatedEvent.getName());
	    
	    return clientMongoEntity;

	}
	
	private void saveClientEntity(ClientMongoEntity clientMongoEntity) {
		
	    clientNoSqlReadRepository.save(clientMongoEntity);
	}
	
	private void logInfoAction(String methodName, String msg) {
		
		String logMessage = String.format("### [%s] -> [%s] -> [ %s ]", this.getClass().getSimpleName(), methodName, msg);
		
		logger.log(Level.INFO, logMessage);
		
	}
		
}
