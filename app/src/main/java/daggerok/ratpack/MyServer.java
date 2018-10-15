package daggerok.ratpack;

import daggerok.services.MyGreetingService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import ratpack.server.RatpackServer;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptor;

@Singleton
public class MyServer {

  @Inject
  Logger log;

  @Inject
  MyGreetingService myGreetingService;

  public void init(@Observes
                   @Priority(Interceptor.Priority.APPLICATION - 100)
                   @Initialized(ApplicationScoped.class) Object init) throws Exception {
    log.info("I'm sorry.. Don't know why, but you need this shit here too... {}", init.toString());
  }

  @SneakyThrows
  @PostConstruct
  public void init() {
    log.info("init ratpack...");
    RatpackServer.start(server -> server
        .handlers(chain -> chain
            .get(ctx -> ctx.render(myGreetingService.hola()))
            .get(":name", ctx -> ctx.render(myGreetingService.hola(ctx.getPathTokens().get("name"))))
        )
    );
  }
}
