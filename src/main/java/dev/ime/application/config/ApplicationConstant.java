package dev.ime.application.config;

public class ApplicationConstant {	
	
	private ApplicationConstant() {
		super();
	}

	public static final String NODATA = "No data available";
	public static final String UNKNOWDATA = "Unknow data";		
	
	public static final String CLIENTID = "ClientId";
	public static final String FLIGHTID = "FlightId";
	public static final String CAT_CLIENT = "Client";
	public static final String CAT_FLIGHT = "Flight";

	public static final String EX_RESOURCE_NOT_FOUND = "ResourceNotFoundException";	
	public static final String EX_RESOURCE_NOT_FOUND_DESC = "Exception is coming, the resource has not been found.";	
	public static final String EX_ENTITY_ASSOCIATED = "EntityAssociatedException";	
	public static final String EX_ENTITY_ASSOCIATED_DESC = "Hear me roar, some entity is still associated in the element";	
	public static final String EX_ILLEGAL_ARGUMENT = "IllegalArgumentException";
	public static final String EX_ILLEGAL_ARGUMENT_DES = "Some argument is not supported";
	
	public static final String EX_METHOD_ARGUMENT_INVALID = "MethodArgumentNotValidException";
	public static final String EX_METHOD_ARGUMENT_INVALID_DESC = "MethodArgument Not Valid Exception";
	public static final String EX_METHOD_ARGUMENT_TYPE = "MethodArgumentTypeMismatchException";
	public static final String EX_METHOD_ARGUMENT_TYPE_DESC = "Method Argument Type Mismatch Exception";
	public static final String EX_JAKARTA_VAL = "ConstraintViolationException";
	public static final String EX_JAKARTA_VAL_DESC = "Constraint Violation Exception";	
	public static final String EX_NO_RESOURCE = "NoResourceFoundException";
	public static final String EX_NO_RESOURCE_DESC = "No Resource Found Exception";
	public static final String EX_EX = "Exception";
	public static final String EX_EX_DESC = "Exception because the night is dark and full of terrors";
	
	public static final String MSG_ILLEGAL_COMMAND = "Command not supported";
	public static final String MSG_ILLEGAL_QUERY = "Query not supported";
	public static final String MSG_ILLEGAL_EVENT = "Event not supported";
	public static final String MSG_NO_HANDLER = "No handler found for type ";
	public static final String SEQ_GEN = "Sequence_General";

	public static final String CLIENT_CREATED = "client.created";
	public static final String CLIENT_UPDATED = "client.updated";
	public static final String CLIENT_DELETED = "client.deleted";
	public static final String FLIGHT_CREATED = "flight.created";
	public static final String FLIGHT_UPDATED = "flight.updated";
	public static final String FLIGHT_DELETED = "flight.deleted";
	public static final String FLIGHT_CLIENT_ADDED = "flight.client.added";
	public static final String FLIGHT_CLIENT_REMOVED = "flight.client.removed";
	
}
