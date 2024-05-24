package dev.ime.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ime.application.dto.ClientDto;
import dev.ime.domain.model.Client;
import dev.ime.infrastructure.entity.ClientMongoEntity;

@Component
public class ClientMapper {

	public ClientMapper() {
		super();
	}

	public Client fromMongoToDomain(ClientMongoEntity mongo) {
		
		Client c = new Client();
		c.setId(mongo.getClientId());
		c.setName(mongo.getName());
		
		return c;
	}

	public List<Client> fromListMongoToListDomain(List<ClientMongoEntity> listMongo) {
		
		if ( listMongo == null ) {
			return new ArrayList<>();
		}
		
		return listMongo.stream()
			.map(this::fromMongoToDomain)
			.toList();	
	}
	
	public ClientDto fromDomainToDto(Client domain) {
		
		return new ClientDto(domain.getId(), domain.getName());
		
	}

	public List<ClientDto> fromListDomainToListDto(List<Client> listDomain) {
		
		if ( listDomain == null ) {
			return new ArrayList<>();
		}
		
		return listDomain.stream()
				.map(this::fromDomainToDto)
				.toList();
	}
}
