package com.hotel.management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootApplication
public class ManagementApplicationTests {

	public static void main(String[] args){
		SpringApplication.run(ManagementApplicationTests.class,args);
	}
}
