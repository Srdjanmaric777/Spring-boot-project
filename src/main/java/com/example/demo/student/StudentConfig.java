package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, JANUARY, 5)
            );

            List<Student> repository1 = repository.findAll();
            Integer count = 0;
            for(Student student : repository1){
                if(student.getEmail().equalsIgnoreCase(
                        mariam.getEmail())) {
                    ++count;
                }
                if(student.getEmail().equalsIgnoreCase(
                        alex.getEmail())) {
                    ++count;
                    ++count;
                }
            }

            if(count == 0) {
                repository.saveAll(
                    List.of(mariam,alex)
                );
            } else if (count == 1) {
                repository.save(alex);
            } else if (count == 2) {
                repository.save(mariam);
            }
        };
    }
}
