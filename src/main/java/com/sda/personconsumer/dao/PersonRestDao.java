package com.sda.personconsumer.dao;

import com.sda.personconsumer.person.Person;

import java.util.List;

public interface PersonRestDao {

    List<Person> getAll();

    Person getById(int personId);

    Person add(Person person);

    Person modify(int personId, Person person);

    Person remove(int personId);

    List <Person> getByDateBetween(String from, String to);

}

