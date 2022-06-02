package com.excercises.excercisesbackend;

import com.excercises.excercisesbackend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcercisesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcercisesBackendApplication.class, args);
    }


    @Autowired
    public Service getService(){
        return new Service();
    }
}
