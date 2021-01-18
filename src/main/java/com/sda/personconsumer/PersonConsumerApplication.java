package com.sda.personconsumer;

import com.sda.personconsumer.dao.PersonRestDao;
import com.sda.personconsumer.dao.PersonRestDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonConsumerApplication {

    public static void main(String[] args) {

        PersonRestDao personRestDao = new PersonRestDaoImpl();

        SpringApplication.run(PersonConsumerApplication.class, args);
    }



}
