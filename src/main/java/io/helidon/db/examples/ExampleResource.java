package io.helidon.db.examples;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Dependent //This ensures that io.helidon.example.jpa.ExampleResource is a discoverable CDI bean.
@Path("tables")
public class ExampleResource {
    private static boolean DEBUG;
    static {
        DEBUG = false;
    }
    @Inject
    @Named("example")
    private DataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getString() throws SQLException {
        Response response = getResponse();
        return response;
    } //eof Response getString() throws SQLException

    private Response getResponse() throws SQLException {
        final StringBuilder sb = new StringBuilder();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps =
                     connection.prepareStatement(" SELECT * FROM FRUIT");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String fruitName = "Fruit: " + rs.getString("FruitName");

                sb.append(fruitName);
                String date;
                date = ", DateEntered: "+ rs.getString("DateEntered");
                sb.append(date);
                sb.append("\n");
                if (DEBUG) {
                    System.out.println(fruitName);
                    System.out.println(date);
                    System.out.println("\n");
                }
            }
        }

        final Response returnValue = Response.ok()
                .entity(sb.toString())
                .build();
        return returnValue;
    }

}
