package dev.ime.infrastructure.repository.read;

import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import dev.ime.infrastructure.entity.FlightMongoEntity;

public interface FlightNoSqlReadRepository extends MongoRepository<FlightMongoEntity, ObjectId>{
	
	Optional<FlightMongoEntity> findFirstByFlightId(UUID id);
}
