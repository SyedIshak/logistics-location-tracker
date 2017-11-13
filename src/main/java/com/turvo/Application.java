package com.turvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class Application.
 */
@SpringBootApplication
public class Application {

    /**
     * The Class Application launches the application with inbuilt tomcat starting
     * at port 8080. The same class loads all the required @configuration for the
     * application to run including connection to Mongo DB server as well.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
