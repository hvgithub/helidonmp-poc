/*

package io.helidon.db.examples;

import io.helidon.microprofile.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

*/
/**
 * The type Main test.
 *//*

class ExmpleResourceTest {

    private static Server server;
    private static String serverUrl;

    */
/**
     * Start the server.
     *//*

    @BeforeAll
    public static void startTheServer() {
        server = Server.create().start();
        serverUrl = "http://localhost:" + server.port();
    }

    */
/**
     * All endpoints test.
     *//*

    @Test
    void testGetTables() {
        Client client = ClientBuilder.newClient();

        String s;

         s = client
                .target(serverUrl)
                .path("tables")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        System.out.println("Output ===>>>" + s + "<<<===");

    }

    */
/**
     * Destroy class.
    *//*

    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }
}*/
