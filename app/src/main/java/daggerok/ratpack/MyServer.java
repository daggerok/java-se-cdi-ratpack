package daggerok.ratpack;

import daggerok.events.SimpleEvent;
import daggerok.services.MyGreetingService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import ratpack.server.RatpackServer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyServer {

  @Inject
  Logger log;

  @Inject
  Event<SimpleEvent> events;

  @Inject
  MyGreetingService myGreetingService;

  public void init(@Observes
                   @Initialized(ApplicationScoped.class) Object init) throws Exception {
    log.info("I'm sorry.. Don't know why, but you need this shit here too... {}", init.toString());
  }

  @SneakyThrows
  @PostConstruct
  public void init() {
    RatpackServer.start(server -> server
        .handlers(chain -> chain
            .get(ctx -> {
              ctx.render(myGreetingService.hola());
              events.fire(SimpleEvent.of("done."));
            })
            .get(":name", ctx -> {
              final String name = ctx.getPathTokens().get("name");
              events.fire(SimpleEvent.of("received: " + name));
              ctx.render(myGreetingService.hola(name));
            })
        )
    );
  }
}
