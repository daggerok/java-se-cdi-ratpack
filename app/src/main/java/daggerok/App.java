package daggerok;

import daggerok.events.MessageListener;
import daggerok.ratpack.MyServer;
import daggerok.services.LoggerService;
import daggerok.services.MyGreetingService;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.BeanManager;

public class App {

  public static void main(String[] args) {
    SeContainerInitializer.newInstance()
                          //.disableDiscovery()
                          .setClassLoader(App.class.getClassLoader())
                          .addPackages(App.class,
                                       MessageListener.class,
                                       BeanManager.class,
                                       MyServer.class,
                                       LoggerService.class,
                                       MyGreetingService.class)
                          .initialize();
    //do not close container unless you wanna receive and fire events...
    //SeContainer container = initializer.initialize();
    //try (SeContainer container = initializer.initialize()) {
    //container.select(LoggerService.class);
    //container.select(MyGreetingService.class);
    //container.select(MyServer.class);
    //}
  }
}
