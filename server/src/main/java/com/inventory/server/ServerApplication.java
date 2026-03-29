package com.inventory.server;
import com.inventory.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Here, The controller's methods should be mentioned to run the project.
// Main file to run the project.
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);

		Controller controller = new Controller();
		controller.generateReport();
	}
}
