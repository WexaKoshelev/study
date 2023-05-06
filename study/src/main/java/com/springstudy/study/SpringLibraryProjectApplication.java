package com.springstudy.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;




@SpringBootApplication
public class SpringLibraryProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringLibraryProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        System.out.println("Swagger path: http://localhost:8081/swagger-ui/index.html");

    }

}

