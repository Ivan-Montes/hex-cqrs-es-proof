package dev.ime.infrastructure.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.application.event.FlightClientAddedEvent;
import dev.ime.application.event.FlightClientRemovedEvent;
import dev.ime.application.event.FlightCreatedEvent;
import dev.ime.application.event.FlightDeletedEvent;
import dev.ime.application.event.FlightUpdatedEvent;
import dev.ime.config.LoggerUtil;
import dev.ime.domain.event.Event;
import dev.ime.domain.port.outbound.FlightProjectorPort;
import dev.ime.infrastructure.entity.ClientMongoEntity;
import dev.ime.infrastructure.entity.FlightMongoEntity;
import dev.ime.infrastructure.repository.read.ClientNoSqlReadRepository;
import dev.ime.infrastructure.repository.read.FlightNoSqlReadRepository;

@Repository
public class FlightProjectorAdapter implements FlightProjectorPort{

	private final FlightNoSqlReadRepository flightNoSqlReadRepository;
	private final ClientNoSqlReadRepository clientNoSqlReadRepository;
	private final LoggerUtil loggerUtil;	
	
	public FlightProjectorAdapter(FlightNoSqlReadRepository flightNoSqlReadRepository,ClientNoSqlReadRepository clientNoSqlReadRepository, LoggerUtil loggerUtil) {
		super();
		this.flightNoSqlReadRepository = flightNoSqlReadRepository;
		this.clientNoSqlReadRepository = clientNoSqlReadRepository;
		this.loggerUtil = loggerUtil;
	}

	@Override
	public void create(Event event) {
		
		if ( event instanceof FlightCreatedEvent flightCreatedEvent) {
			
			FlightMongoEntity flightMongoEntity = buildClientEntity(flightCreatedEvent);
			
			saveFlightEntity(flightMongoEntity);
			
			logInfo("FlightCreatedEvent", flightMongoEntity.toString());

		} 
		
	}

	@Override
	public void update(Event event) {

		if ( event instanceof FlightUpdatedEvent flightUpdatedEvent) {
			
			UUID id = flightUpdatedEvent.getFlightId();
			Optional<FlightMongoEntity> optFlightFound = flightNoSqlReadRepository.findFirstByFlightId(id);
			
			if ( optFlightFound.isEmpty() ) {
				
				logInfo("FlightUpdatedEvent] -> [ResourceNotFoundException", ApplicationConstant.FLIGHTID + " : "  + id);
				return;
			}
			
			FlightMongoEntity flightFound = optFlightFound.get();
			flightFound.setOrigin(flightUpdatedEvent.getOrigin());
			flightFound.setDestiny(flightUpdatedEvent.getDestiny());
			
			saveFlightEntity(flightFound);
			
			logInfo("FlightUpdatedEvent", flightFound.toString());

		} 
		
	}

	@Override
	public void deleteById(Event event) {

		if ( event instanceof FlightDeletedEvent flightDeletedEvent) {			
			
			UUID id = flightDeletedEvent.getFlightId();
			Optional<FlightMongoEntity> optFlightFound = flightNoSqlReadRepository.findFirstByFlightId(id);
			
			if ( optFlightFound.isEmpty() ) {
				
				logInfo("FlightDeletedEvent] -> [ResourceNotFoundException", ApplicationConstant.FLIGHTID + " : "  + id);
				return;
			}
			
			flightNoSqlReadRepository.delete(optFlightFound.get());
			
			logInfo("FlightDeletedEvent", ApplicationConstant.FLIGHTID + " : "  + id);

		} 
		
	}

	@Override
	public void addClientInFlight(Event event) {

		if ( event instanceof FlightClientAddedEvent flightClientAddedEvent) {
			
			UUID flightId = flightClientAddedEvent.getFlightId();
			UUID clientId = flightClientAddedEvent.getClientId();
		
			Optional<FlightMongoEntity> optFlightFound = flightNoSqlReadRepository.findFirstByFlightId(flightId);			
			if ( optFlightFound.isEmpty() ) {
				
				logInfo("FlightClientAddedEvent] -> [ResourceNotFoundException", ApplicationConstant.FLIGHTID + " : "  + flightId);
				return;
			}
			
			Optional<ClientMongoEntity>optClientFound = clientNoSqlReadRepository.findFirstByClientId(clientId);
			if ( optClientFound.isEmpty() ) {
				
				logInfo("FlightClientAddedEvent] -> [ResourceNotFoundException", ApplicationConstant.CLIENTID + " : "  + clientId);
				return;
			}
			
			FlightMongoEntity flightFound = optFlightFound.get();
			ClientMongoEntity clientFound = optClientFound.get();
			
			flightFound.getClientSet().add(clientFound);
			saveFlightEntity(flightFound);
			
			logInfo("FlightClientAddedEvent", flightFound.toString());

		} 
		
	}

	@Override
	public void removeClientInFlight(Event event) {

		if ( event instanceof FlightClientRemovedEvent flightClientRemovedEvent) {
			
			UUID flightId = flightClientRemovedEvent.getFlightId();
			UUID clientId = flightClientRemovedEvent.getClientId();

			Optional<FlightMongoEntity> optFlightFound = flightNoSqlReadRepository.findFirstByFlightId(flightId);			
			if ( optFlightFound.isEmpty() ) {
				
				logInfo("FlightClientRemovedEvent] -> [ResourceNotFoundException", ApplicationConstant.FLIGHTID + " : "  + flightId);
				return;
			}
			
			Optional<ClientMongoEntity>optClientFound = clientNoSqlReadRepository.findFirstByClientId(clientId);
			if ( optClientFound.isEmpty() ) {
				
				logInfo("FlightClientRemovedEvent] -> [ResourceNotFoundException", ApplicationConstant.CLIENTID + " : "  + clientId);
				return;
			}
			
			FlightMongoEntity flightFound = optFlightFound.get();
			ClientMongoEntity clientFound = optClientFound.get();			

			flightFound.getClientSet().remove(clientFound);
			flightNoSqlReadRepository.save(flightFound);
			logInfo("FlightClientRemovedEvent", flightFound.toString());

		} 
		
	}
	
	private FlightMongoEntity buildClientEntity(FlightCreatedEvent flightCreatedEvent) {
		
		FlightMongoEntity flightMongoEntity = new FlightMongoEntity();
		flightMongoEntity.setFlightId(flightCreatedEvent.getFlightId());
		flightMongoEntity.setOrigin(flightCreatedEvent.getOrigin());
		flightMongoEntity.setDestiny(flightCreatedEvent.getDestiny());
		
		return flightMongoEntity;
		
	}

	private void saveFlightEntity(FlightMongoEntity flightMongoEntity) {
		
		flightNoSqlReadRepository.save(flightMongoEntity);
		
	}	

	private void logInfo(String action, String clientInfo) {
		
	    loggerUtil.logInfoAction(this.getClass().getSimpleName(), action, clientInfo);
	    
	}
	
}
