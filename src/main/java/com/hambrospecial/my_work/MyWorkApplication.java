package com.hambrospecial.my_work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWorkApplication.class, args);
	}

}
