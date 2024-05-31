package dev.ime.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import dev.ime.domain.event.Event;
import dev.ime.domain.port.outbound.FlightNoSqlWriteRepositoryPort;
import dev.ime.infrastructure.repository.write.FlightNoSqlWriteRepository;

@Repository
public class FlightNoSqlWriteRepositoryAdapter implements FlightNoSqlWriteRepositoryPort{

	private final FlightNoSqlWriteRepository flightNoSqlWriteRepository;
	
	public FlightNoSqlWriteRepositoryAdapter(FlightNoSqlWriteRepository flightNoSqlWriteRepository) {
		super();
		this.flightNoSqlWriteRepository = flightNoSqlWriteRepository;
	}

	@Override
	public void save(Event event) {
		
		flightNoSqlWriteRepository.save(event);
		
	}

}
