package com.example.server.controller;

import com.example.server.entity.Person;
import com.example.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Person> getPersonList(){
        return personService.findPersonList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id){
        return personService.findPerson(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@RequestParam(value = "id") int id,
                            @RequestParam(value = "gender") int gender,
                            @RequestParam(value = "birth") int birth,
                            @RequestParam(value = "mileage") int mileage,
                            @RequestParam(value = "hour") int hour) {
        int t = personService.add(id, gender, birth, mileage, hour);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public String delPerson(@RequestParam(value = "id") int id) {
        int t = personService.delete(id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updatePerson(@RequestParam(value = "id") int id,
                            @RequestParam(value = "gender") int gender,
                            @RequestParam(value = "birth") int birth,
                            @RequestParam(value = "mileage") int mileage,
                            @RequestParam(value = "hour") int hour) {
        int t = personService.update(id, gender, birth, mileage, hour);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

}
