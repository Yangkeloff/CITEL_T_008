package com.example.cit08server.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.cit08server.entity.Person;
import com.example.cit08server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @RequestMapping(value = "/nums", method = RequestMethod.GET)
    public List<Integer> getNums(
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
        int typeTotal = personService.selectTotal(typeStr, start, end);
        int allTotal = personService.allTotal();
        List<Integer> res = new ArrayList<>();
        res.add(typeTotal);
        res.add(allTotal);
        return res;
    }

    @RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id){
        return personService.getPerson(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@RequestBody String jsonString) {
        JSONObject personObj = JSONObject.parseObject(jsonString);
        int id = personObj.getIntValue("id");
        int gender = personObj.getIntValue("gender");
        int birth = personObj.getIntValue("birth");
        int mileage = personObj.getIntValue("mileage");
        int hour = personObj.getIntValue("hour");
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
