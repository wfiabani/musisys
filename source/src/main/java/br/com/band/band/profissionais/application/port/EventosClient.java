package br.com.band.band.profissionais.application.port;

import java.util.UUID;

public interface EventosClient {

    boolean hasPastEventAssignment(UUID professionalId);
}
