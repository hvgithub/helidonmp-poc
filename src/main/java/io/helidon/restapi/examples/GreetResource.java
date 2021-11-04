package io.helidon.restapi.examples;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.logging.Logger;

//This class is annotated with Path which sets the path for this resource as /greet
@Path("/greet")

//The RequestScoped annotation defines that this bean is request scoped. The request scope is active only for the duration of one web service invocation and it is destroyed at the end of that invocation. You can learn more about scopes and contexts, 
@RequestScoped

//
public class GreetResource {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final GreetingProvider greetingProvider; //iteration - 2 use confg file prop


//This updated class adds a GreetingProvider and uses constructor injection to get the value from the configuration file.
    @Inject
    public GreetResource(GreetingProvider greetingConfig) {
        this.greetingProvider = greetingConfig;
    }

    /* A public JsonObject getDefaultMessage() method is defined which is annotated with GET, 
	meaning it will accept the HTTP GET method. 
	It is also annotated with Produces(MediaType.APPLICATION_JSON)
 	 which declares that this method will return JSON data.
     */
 /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject v1getDefaultMessage() {
        /* The method body creates a JSON object containing 
	a single object named "message" with the content "Hello World". 
	This method will be expanded and improved later in the tutorial.
         
        return JSON.createObjectBuilder()
                .add("message", "Hello World")
                .build();
    }
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a generic greeting",
            description = "Greets the user generically")
    @APIResponse(description = "Simple JSON containing the greeting",
            content = @Content(mediaType = "application/json"))
    
    @Timed
    public JsonObject getDefaultMessage() {
        return createResponse("World");
    }

    private JsonObject createResponse(String who) {
        String msg = String.format("%s %s!", greetingProvider.getMessage(), who);

        LOGGER.info("Message=" + msg);
        return JSON.createObjectBuilder()
                .add("message", msg)
                .build();
    }
    
    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getMessage(@PathParam("name") String name) {
        return createResponse(name);
    }


    /*

    The first of these two methods implements a new HTTP GET service that returns JSON and it has a path parameter. The Path annotation defines the next part of the path to be a parameter named name. In the method arguments the PathParam("name") annotation on String name has the effect of passing the parameter from the URL into this method as name.
    */
    @Path("/greeting")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGreeting(JsonObject jsonObject) {

        if (!jsonObject.containsKey("greeting")) {
            JsonObject entity = JSON.createObjectBuilder()
                    .add("error", "No greeting provided")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }

        String newGreeting = jsonObject.getString("greeting");

        greetingProvider.setMessage(newGreeting);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
