package com.example.dev1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.security.MessageDigest;
import java.util.Locale;

@SpringBootApplication
public class Dev1Application {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en"));

		SpringApplication.run(Dev1Application.class, args);
	}

	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("Messages");
		return ms;
	}

}
