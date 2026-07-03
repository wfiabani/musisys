package br.com.band.band.profissionais.application.port;

public interface DomainEventPublisher {

    void publish(Object event);
}
