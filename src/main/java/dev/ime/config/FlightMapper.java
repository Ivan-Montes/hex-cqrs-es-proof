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
	
	public Flight fromMongoToDomain(FlightMongoEntity mongo) {
		
		Flight f = new Flight();
		f.setId(mongo.getFlightId());
		f.setOrigin(mongo.getOrigin());
		f.setDestiny(mongo.getDestiny());
		f.setClientSet(mongo.getClientSet()
							.stream()
							.map( cm -> {
								
								Client c = new Client();
								c.setId(cm.getClientId());
								c.setName(cm.getName());
								
								return c;
							})
							.collect(Collectors.toSet())
						);		
		return f;
	}
	
	public List<Flight> fromListMongoToListDomain(List<FlightMongoEntity> listMongo) {
		
		if ( listMongo == null ) {
			return new ArrayList<>();
		}
		
		return listMongo.stream()
			.map(this::fromMongoToDomain)
			.toList();	
	}

	public FlightDto fromDomainToDto(Flight domain) {
		
		return new FlightDto(
				domain.getId(),
				domain.getOrigin(),
				domain.getDestiny(),
				domain.getClientSet()
						.stream()
						.map( c -> new ClientDto(c.getId(), c.getName()) )				
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
