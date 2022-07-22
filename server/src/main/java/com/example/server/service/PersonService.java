package com.example.server.service;

import com.example.server.dao.PersonMapper;
import com.example.server.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private  PersonMapper personMapper;

    public int add(int id, int gender, int birth,
                   int mileage, int hour){
        return personMapper.add(id, gender, birth, mileage, hour);
    }

    public int update(int id, int gender, int birth,
                   int mileage, int hour){
        return personMapper.update(id, gender, birth, mileage, hour);
    }

    public int delete(int id) {
        return personMapper.delete(id);
    }

    public List<Person> findPersonList() {
        return personMapper.findAllPerson();
    }

    public Person findPerson(int id) {
        return personMapper.findPerson(id);
    }
}
