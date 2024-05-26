package dev.ime.application.event;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.domain.event.Event;

public final class ClientUpdatedEvent extends Event {

	private final UUID clientId;
    private final String name;
    
    @JsonCreator
	public ClientUpdatedEvent(@JsonProperty("sequence")Long sequence, @JsonProperty("clientId")UUID clientId, @JsonProperty("name")String name) {
		super(ApplicationConstant.CAT_CLIENT, ApplicationConstant.CLIENT_UPDATED, sequence);
		this.clientId = clientId;
		this.name = name;
	}

	public UUID getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ClientUpdatedEvent [clientId=" + clientId + ", name=" + name + ", eventId=" + eventId
				+ ", eventCategory=" + eventCategory + ", eventType=" + eventType + ", timeInstant=" + timeInstant
				+ ", sequence=" + sequence + "]";
	}    
	
}
