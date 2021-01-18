package com.sda.personconsumer.dao;

import com.sda.personconsumer.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PersonRestDaoImpl implements PersonRestDao {

    @Value("${connection.address}")
    String address;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<Person> getAll() {
        try {
            ResponseEntity<Person[]> persons = restTemplate.getForEntity(address + "persons", Person[].class);
            Person[] people = persons.getBody();
            return Arrays.stream(people).collect(Collectors.toList());
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }


    @Override
    public Person getById(int personId) {
        ResponseEntity<Person> person = restTemplate.getForEntity(address + "person/id/" + personId, Person.class);
        return person.getBody();
    }

    @Override
    public Person add(Person person) {
        ResponseEntity<Person> personAdd = restTemplate.postForEntity(address + "persons", person, Person.class);
        return personAdd.getBody();
    }

    @Override
    public Person modify(int personId, Person person) {
        restTemplate.put(address + "persons/" + personId, person);
        return getById(personId);
    }

    @Override
    public Person remove(int personId) {
        Person byId = getById(personId);
        restTemplate.delete(address + "person/delete/" + personId);
        return byId;
    }

    @Override
    public List<Person> getByDateBetween(String from, String to) {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("from",from);
        stringMap.put("to",to);
        ResponseEntity<Person[]> personAdd = restTemplate.getForEntity(address+"/person/byDateBetween?from="+from+"&to="+to,Person[].class);
        return Arrays.stream(personAdd.getBody()).collect(Collectors.toList());
    }

}
