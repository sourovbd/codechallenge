package com.ehrlich.codechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {"com.ehrlich.codechallenge.entity"})
public class CodechallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodechallengeApplication.class, args);
	}

}
