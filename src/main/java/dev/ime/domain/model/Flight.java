package dev.ime.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Flight {

	private UUID id;
	private String origin;
	private String destiny;
	private Set<Client> clientSet = new HashSet<>();

	public Flight() {
		super();
	}
	public Flight(UUID id, String origin, String destiny, Set<Client> clientSet) {
		super();
		this.id = id;
		this.origin = origin;
		this.destiny = destiny;
		this.clientSet = clientSet;
	}
	
	public UUID getId() {
		return id;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestiny() {
		return destiny;
	}
	public Set<Client> getClientSet() {
		return clientSet;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}
	public void setClientSet(Set<Client> clientSet) {
		this.clientSet = clientSet;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientSet, destiny, id, origin);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(clientSet, other.clientSet) && Objects.equals(destiny, other.destiny)
				&& Objects.equals(id, other.id) && Objects.equals(origin, other.origin);
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", origin=" + origin + ", destiny=" + destiny + ", clientSet=" + clientSet + "]";
	}
	
}
