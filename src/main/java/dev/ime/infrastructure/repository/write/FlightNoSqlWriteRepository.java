package dev.ime.infrastructure.repository.write;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.ime.domain.event.Event;

public interface FlightNoSqlWriteRepository extends MongoRepository <Event, UUID> {

}
