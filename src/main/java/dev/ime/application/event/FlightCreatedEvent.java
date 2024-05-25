package dev.ime.application.event;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dev.ime.application.config.ApplicationConstant;
import dev.ime.domain.event.Event;

public final class FlightCreatedEvent extends Event {

	private final UUID flightId;	
	private final String origin;	
	private final String destiny;

    @JsonCreator
	public FlightCreatedEvent(@JsonProperty("sequence")Long sequence, @JsonProperty("flightId")UUID flightId, @JsonProperty("origin")String origin, @JsonProperty("destiny")String destiny) {
		super(ApplicationConstant.CAT_FLIGHT, ApplicationConstant.FLIGHT_CREATED, sequence);
		this.flightId = flightId;
		this.origin = origin;
		this.destiny = destiny;
	}

	public UUID getFlightId() {
		return flightId;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestiny() {
		return destiny;
	}

	@Override
	public String toString() {
		return "FlightCreatedEvent [flightId=" + flightId + ", origin=" + origin + ", destiny=" + destiny + ", eventId="
				+ eventId + ", eventCategory=" + eventCategory + ", eventType=" + eventType + ", timeInstant="
				+ timeInstant + ", sequence=" + sequence + "]";
	}
	
}
