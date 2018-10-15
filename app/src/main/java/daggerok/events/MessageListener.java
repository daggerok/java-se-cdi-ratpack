package daggerok.events;

import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MessageListener {

  @Inject
  Logger log;

  public void observeEvent(@Observes final SimpleEvent message) {
    log.info("observe message: {}", message);
  }
}
