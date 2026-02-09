package br.com.band.band.repertorio.application.port;

public interface DomainEventPublisher {

    void publish(Object event);
}
