package com.example.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
@MapperScan("com.example.backend.mapper")
public class MessageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageSystemApplication.class, args);
	}


}
