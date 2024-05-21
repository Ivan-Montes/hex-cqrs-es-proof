package dev.ime.infrastructure.entity;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Document("clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class ClientMongoEntity {

	@Id
	private ObjectId mongoId;
	
	@Indexed(unique = true)
	private UUID clientId;
	
	private String name;
	
}
