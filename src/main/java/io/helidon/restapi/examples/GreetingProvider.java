package io.helidon.restapi.examples;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

//This class also has the ApplicationScoped annotation, so it will persist for the life of the application.
@ApplicationScoped
public class GreetingProvider {
    private final static Logger LOGGER = Logger.getLogger(GreetingProvider.class.getName());

    private final AtomicReference<String> message = new AtomicReference<>();

    /*

The public GreetingProvider constructor is annotated with Inject which tells Helidon to use Contexts and Dependency Injection to provide the needed values. In this case, the String message is annotated with ConfigProperty(name = "app.greeting") so Helidon will inject the property from the configuration file with the key app.greeting. This method demonstrates how to read configuration information into the application. A getter and setter are also included in this class.
     */
    @Inject
    public GreetingProvider(@ConfigProperty(name = "app.greeting") String message) {
        this.message.set(message);
        LOGGER.info("Message=" + message);
    }

    String getMessage() {
        return message.get();
    }

    void setMessage(String message) {
        this.message.set(message);
    }
}
