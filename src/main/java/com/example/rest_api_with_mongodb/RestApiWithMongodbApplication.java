package com.example.rest_api_with_mongodb;

import com.example.rest_api_with_mongodb.model.Address;
import com.example.rest_api_with_mongodb.model.Student;
import com.example.rest_api_with_mongodb.model.enums.Gender;
import com.example.rest_api_with_mongodb.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RestApiWithMongodbApplication {

    public static void main (String[] args) {
        SpringApplication.run(RestApiWithMongodbApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address("Poland", "11-111", "Warsaw");

            String email = "none@gmail.com";
            Student student = new Student(
                    "vl",
                    "pr",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );

          //  usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println(s + " already exists");

                    }, () -> {
                        System.out.println("inserting student " + student);
                        repository.insert(student);
                    });


        };
    }

    private void usingMongoTemplateAndQuery (StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalArgumentException("found many students with email " + email);
        }

        if (students.isEmpty()) {
            System.out.println("inserting student " + student);
            repository.insert(student);
        } else {
            System.out.println(student + " already exists");
        }
    }

}
