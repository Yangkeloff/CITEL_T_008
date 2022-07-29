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
    public List<Person> findAllPerson(
            @RequestParam(value = "type") int type,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "end") int end,
            @RequestParam(value = "curPage") int curPage,
            @RequestParam(value = "pageSize") int pageSize){
        String typeStr = "";
        if (type == 1) {
            typeStr = "birth";
        }else if (type == 2) {
            typeStr = "mileage";
        }else if (type == 3) {
            typeStr = "hour";
        }
        int from = pageSize * (curPage - 1);
        return personService.findAllPerson(typeStr, start, end, from, pageSize);
    }
    @RequestMapping(value = "/list/total", method = RequestMethod.GET)
    public Integer selectTotal(
            @RequestParam(value = "type") int type,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "end") int end){
        String typeStr = "";
        if (type == 1) {
            typeStr = "birth";
        }else if (type == 2) {
            typeStr = "mileage";
        }else if (type == 3) {
            typeStr = "hour";
        }
        return personService.selectTotal(typeStr, start, end);
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
