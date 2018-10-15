package daggerok;

import daggerok.ratpack.MyServer;
import daggerok.services.LoggerService;
import daggerok.services.MyGreetingService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@Slf4j
public class App {

  public static void main(String[] args) {
    final SeContainerInitializer initializer = SeContainerInitializer.newInstance()
                                                                     //.disableDiscovery()
                                                                     .setClassLoader(App.class.getClassLoader())
                                                                     .addPackages(App.class,
                                                                                  MyServer.class,
                                                                                  LoggerService.class,
                                                                                  MyGreetingService.class);
    try (SeContainer container = initializer.initialize()) {
      //container.select(LoggerService.class);
      //container.select(MyGreetingService.class);
      //container.select(MyServer.class);
    }
  }
}
