package com.tushar.resumeai;

import com.tushar.resumeai.service.ParserService;
import jakarta.el.BeanNameResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ResumeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAiApplication.class, args);
	}


}
