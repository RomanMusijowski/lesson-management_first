package com.roman_musijowski.pgs_lessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PgsLessonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgsLessonsApplication.class, args);
	}

}
