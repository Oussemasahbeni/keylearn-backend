package com.oussema.keylearn;

import org.springframework.boot.SpringApplication;

public class TestKeylearnApplication {

	public static void main(String[] args) {
		SpringApplication.from(KeylearnApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
