package com.vin.tutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student alvin = new Student(
                    "Alvin",
                    "alvinroidoma72@gmail.com",
                    LocalDate.of(1999, Month.MAY,05)
            );

            Student alven = new Student(
                    "Alven",
                    "alvenhoshinovv@gmail.com",
                    LocalDate.of(1999, Month.MAY,05)
            );
            repository.saveAll(Arrays.asList(alvin,alven));
        };
    }
}
