package com.main;

import io.helidon.microprofile.server.Server;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.*;


public final class Main {

    private Main() {
    }

    //private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(final String[] args) throws IOException {
        Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/greet");

        setUpLogger();

        LOGGER.info("First msg");
        LOGGER.info("Second msg");

        // ==> will not work since there is a global hanlder for logging LOGGER.setLevel(Level.FINE);

        /*
        SEVERE
        WARNING
        INFO
        CONFIG
        FINE
        FINER
        FINEST
         */
    }

    private static void setUpLogger() {
        // To override root log manager
        // Root Handler Reset
        LogManager.getLogManager().reset();
        // Set level here
        LOGGER.setLevel(Level.FINE);

        // Console log handler
        ConsoleHandler ch = new ConsoleHandler();

        //What to be displayed in console
        // Background - no one should notice
        ch.setLevel(Level.FINE);
        LOGGER.addHandler(ch);

        /*
        try {
            FileHandler fh = new FileHandler();
            //fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"File not working" , e);
        } */
    }

    static Server startServer() {
        return Server.create().start();
    }

}