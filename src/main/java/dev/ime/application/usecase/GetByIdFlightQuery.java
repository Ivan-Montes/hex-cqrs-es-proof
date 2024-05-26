package dev.ime.application.usecase;

import java.util.UUID;

import dev.ime.domain.query.Query;

public record GetByIdFlightQuery( UUID id ) implements Query  {

}
