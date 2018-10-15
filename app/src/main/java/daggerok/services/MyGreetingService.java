package daggerok.services;

import daggerok.events.SimpleEvent;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.beans.SimpleBeanInfo;
import java.util.Objects;

public class MyGreetingService {

  @Inject
  Logger log;

  @Inject
  Event<SimpleEvent> events;

  public String hola(final String name) {
    events.fire(SimpleEvent.of(name));
    log.info(name);
    Objects.requireNonNull(name);
    return "Welcome, " + name + "!";
  }

  public String hola() {
    events.fire(new SimpleEvent());
    log.info("hola...");
    return hola("ratpack");
  }
}
