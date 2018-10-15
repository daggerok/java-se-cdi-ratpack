package daggerok.services;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Objects;

public class MyGreetingService {

  @Inject
  Logger log;

  public String hola(final String name) {
    log.info(name);
    Objects.requireNonNull(name);
    return "Welcome, " + name + "!";
  }

  public String hola() {
    log.info("hola...");
    return hola("ratpack");
  }
}
