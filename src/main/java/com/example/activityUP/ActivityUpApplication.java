package com.example.activityUP;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@MapperScan("com.example.activityUP.mapper")
@SpringBootApplication
public class ActivityUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityUpApplication.class, args);
	}

}
