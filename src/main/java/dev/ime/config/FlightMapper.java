package dev.ime.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.ime.application.dto.ClientDto;
import dev.ime.application.dto.FlightDto;
import dev.ime.domain.model.Client;
import dev.ime.domain.model.Flight;
import dev.ime.infrastructure.entity.FlightMongoEntity;

@Component
public class FlightMapper {

	public FlightMapper() {
		super();
	}
	
	public Flight fromMongoToDomain(FlightMongoEntity flightMongoEntity) {
		
		Flight flight = new Flight();
		flight.setId(flightMongoEntity.getFlightId());
		flight.setOrigin(flightMongoEntity.getOrigin());
		flight.setDestiny(flightMongoEntity.getDestiny());
		flight.setClientSet(flightMongoEntity.getClientSet()
							.stream()
							.map( clientMongoEntity -> {
								
								Client client = new Client();
								client.setId(clientMongoEntity.getClientId());
								client.setName(clientMongoEntity.getName());
								
								return client;
							})
							.collect(Collectors.toSet())
						);		
		return flight;
	}
	
	public List<Flight> fromListMongoToListDomain(List<FlightMongoEntity> listMongo) {
		
		if ( listMongo == null ) {
			return new ArrayList<>();
		}
		
		return listMongo.stream()
			.map(this::fromMongoToDomain)
			.toList();	
	}

	public FlightDto fromDomainToDto(Flight domainEntity) {
		
		return new FlightDto(
				domainEntity.getId(),
				domainEntity.getOrigin(),
				domainEntity.getDestiny(),
				domainEntity.getClientSet()
						.stream()
						.map( client -> new ClientDto(client.getId(), client.getName()) )				
						.collect(Collectors.toSet())
				);
		
	}
	
	public List<FlightDto> fromListDomainToListDto(List<Flight> listDomain) {
		
		if ( listDomain == null ) {
			return new ArrayList<>();
		}
		
		return listDomain.stream()
				.map(this::fromDomainToDto)
				.toList();
	}
}
