package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication{
    //extends SpringBootServletInitializer
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
        System.out.print("Lubie placki");

        System.out.println(System.getenv("JDBC_DATABASE_URL"));
        System.out.println(System.getenv("JDBC_DATABASE_USERNAME"));
        System.out.println(System.getenv("JDBC_DATABASE_PASSWORD"));

    }
//@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//       return application.sources(TasksApplication.class);
//}
}
