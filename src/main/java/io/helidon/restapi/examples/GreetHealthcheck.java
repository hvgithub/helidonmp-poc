/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.helidon.restapi.examples;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class GreetHealthcheck implements HealthCheck {

    private GreetingProvider provider;

    @Inject
    public GreetHealthcheck(GreetingProvider provider) {
        this.provider = provider;
    }

    @Override
    public HealthCheckResponse call() {
        String message = provider.getMessage();
        return HealthCheckResponse.named("greeting")
                .state("Hello".equals(message))
                .withData("greeting", message)
                .build();
    }
}
