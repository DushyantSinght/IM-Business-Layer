package com.inventory.server;
import com.inventory.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

// Here, The controller's methods should be mentioned to run the project.
// Main file to run the project.
@SpringBootApplication
@ComponentScan(basePackages = "com.inventory")
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);


	}
}
