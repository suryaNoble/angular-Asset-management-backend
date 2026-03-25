package com.example.help_desk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.help_desk.config.SecurityConfig;



//@SpringBootApplication(scanBasePackages = "com.example.help_desk")
@SpringBootApplication
@Import(SecurityConfig.class)
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

}
