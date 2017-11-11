package com.turvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class Application.
 */
@SpringBootApplication
public class Application {
	
	/**
	 * The main method brings up the tomcat instance and loads all the beans to the bean container.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
