package dev.ime.infrastructure.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Document("flights")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class FlightMongoEntity {

	@Id
	private ObjectId mongoId;
	
	@Indexed(unique = true)
	private UUID flightId;
	
	private String origin;
	
	private String destiny;
	
	@DBRef
	private Set<ClientMongoEntity>clientSet = new HashSet<>();
	
}
