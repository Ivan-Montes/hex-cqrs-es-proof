package dev.ime.application.event;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.domain.event.Event;

public class FlightClientAddedEvent extends Event {

	private final UUID flightId;
	private final UUID clientId;

    @JsonCreator
	public FlightClientAddedEvent(@JsonProperty("sequence")Long sequence, @JsonProperty("flightId")UUID flightId, @JsonProperty("clientId")UUID clientId) {
		super(ApplicationConstant.CAT_FLIGHT, ApplicationConstant.FLIGHT_CLIENT_ADDED, sequence);
		this.flightId = flightId;
		this.clientId = clientId;
	}

	public UUID getFlightId() {
		return flightId;
	}

	public UUID getClientId() {
		return clientId;
	}

	@Override
	public String toString() {
		return "FlightClientAddedEvent [flightId=" + flightId + ", clientId=" + clientId + ", eventId=" + eventId
				+ ", eventCategory=" + eventCategory + ", eventType=" + eventType + ", timeInstant=" + timeInstant
				+ ", sequence=" + sequence + "]";
	}

}
