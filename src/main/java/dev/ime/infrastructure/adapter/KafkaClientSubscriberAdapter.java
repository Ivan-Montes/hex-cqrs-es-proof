package dev.ime.infrastructure.adapter;

import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.ime.domain.event.Event;
import dev.ime.domain.port.inbound.ClientSubscriberPort;
import dev.ime.domain.port.outbound.ClientProjectorPort;
import dev.ime.application.config.ApplicationConstant;

@Component
public class KafkaClientSubscriberAdapter implements ClientSubscriberPort{

	private final Logger logger;
	private final ClientProjectorPort clientProjectorPort;
	
	public KafkaClientSubscriberAdapter(Logger logger, ClientProjectorPort clientProjectorPort) {
		super();
		this.logger = logger;
		this.clientProjectorPort = clientProjectorPort;
	}

	@Override
	@KafkaListener(topics = {ApplicationConstant.CLIENT_CREATED, ApplicationConstant.CLIENT_UPDATED, ApplicationConstant.CLIENT_DELETED}, groupId = "client-consumer-01")
	public void onMessage(ConsumerRecord<String, Object> consumerRecord) {
		
		logger.info("### [KafkaClientSubscriberAdapter] -> [onMessage] -> [Received]");

		switch ( consumerRecord.topic() ) {
		
		case ApplicationConstant.CLIENT_CREATED -> clientProjectorPort.create( (Event)consumerRecord.value() );
		case ApplicationConstant.CLIENT_UPDATED -> clientProjectorPort.update( (Event)consumerRecord.value() );
		case ApplicationConstant.CLIENT_DELETED -> clientProjectorPort.deleteById( (Event)consumerRecord.value() );
		default -> logger.warning("### [KafkaClientSubscriberAdapter] -> [onMessage] -> [Switch] -> [Default Response]");

		}
		
	}

}
