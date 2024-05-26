package dev.ime.application.usecase;

import java.util.UUID;

import dev.ime.domain.query.Query;

public record GetByIdClientQuery( UUID id ) implements Query  {

}
