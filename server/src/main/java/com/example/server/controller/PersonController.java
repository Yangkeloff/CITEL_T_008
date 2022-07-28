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
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public List<Person> findAllPerson(@RequestParam(value = "pageSize") int pageSize,
                                      @RequestParam(value = "curPage") int curPage){
        int start = pageSize * (curPage - 1);
        return personService.findAllPerson(start, pageSize);
    }
    @RequestMapping(value = "/list/birth", method = RequestMethod.GET)
    public List<Person> findPersonByBirth(@RequestParam(value = "start") int start,
                                          @RequestParam(value = "end") int end){
        return personService.findPersonByBirth(start, end);
    }

    @RequestMapping(value = "/list/mile", method = RequestMethod.GET)
    public List<Person> findPersonByMile(@RequestParam(value = "start") int start,
                                         @RequestParam(value = "end") int end){
        return personService.findPersonByMile(start, end);
    }

    @RequestMapping(value = "/list/hour", method = RequestMethod.GET)
    public List<Person> findPersonByHour(@RequestParam(value = "start") int start,
                                         @RequestParam(value = "end") int end){
        return personService.findPersonByHour(start, end);
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
