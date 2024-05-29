package dev.ime.infrastructure.adapter;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.ime.domain.event.Event;
import dev.ime.domain.port.inbound.FlightSubscriberPort;
import dev.ime.domain.port.outbound.FlightProjectorPort;
import dev.ime.application.config.ApplicationConstant;
import dev.ime.config.LoggerUtil;

@Component
public class KafkaFlightSubscriberAdapter implements FlightSubscriberPort{

	private final FlightProjectorPort flightProjectorPort;
	private final LoggerUtil loggerUtil;	
	
	public KafkaFlightSubscriberAdapter(LoggerUtil loggerUtil, FlightProjectorPort flightProjectorPort) {
		super();
		this.flightProjectorPort = flightProjectorPort;
		this.loggerUtil = loggerUtil;
	}

	@Override
	@KafkaListener(topics = {ApplicationConstant.FLIGHT_CREATED, ApplicationConstant.FLIGHT_UPDATED, ApplicationConstant.FLIGHT_DELETED, ApplicationConstant.FLIGHT_CLIENT_ADDED, ApplicationConstant.FLIGHT_CLIENT_REMOVED}, groupId = "flight-consumer-01")
	public void onMessage(ConsumerRecord<String, Object> consumerRecord) {
		
		loggerUtil.logInfoAction(this.getClass().getSimpleName(),"onMessage", "received");

		switch ( consumerRecord.topic() ) {
		
		case ApplicationConstant.FLIGHT_CREATED -> flightProjectorPort.create(  (Event)consumerRecord.value() );
		case ApplicationConstant.FLIGHT_UPDATED -> flightProjectorPort.update( (Event)consumerRecord.value() );
		case ApplicationConstant.FLIGHT_DELETED -> flightProjectorPort.deleteById(  (Event)consumerRecord.value() );
		case ApplicationConstant.FLIGHT_CLIENT_ADDED -> flightProjectorPort.addClientInFlight( (Event)consumerRecord.value() );
		case ApplicationConstant.FLIGHT_CLIENT_REMOVED -> flightProjectorPort.removeClientInFlight( (Event)consumerRecord.value() );
		default -> loggerUtil.logInfoAction(this.getClass().getSimpleName(),"onMessage", "Default Response");

		}
		
	}

}
