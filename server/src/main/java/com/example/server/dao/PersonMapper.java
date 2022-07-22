package com.example.server.dao;

import com.example.server.entity.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonMapper {
    @Insert("insert into person(id, gender, birth, mileage, `hour`) values\n" +
            "(${id}, ${gender}, ${birth}, ${mileage}, ${hour})")
    int add(@Param("id") Integer id, @Param("gender") Integer gender,
            @Param("birth") Integer birth, @Param("mileage") Integer mileage,
            @Param("hour") Integer hour);

    @Select("select * from person where id = #{id}")
    Person findPerson(@Param("id") int id);
    @Update("update person set gender = #{gender}, birth = #{birth}, mileage = #{mileage}, hour = #{hour} where id = #{id}")
    int update(@Param("id") Integer id, @Param("gender") Integer gender,
            @Param("birth") Integer birth, @Param("mileage") Integer mileage,
            @Param("hour") Integer hour);

    @Delete("delete from person where id = #{id}")
    int delete(int id);

    @Select("select * from person")
    List<Person> findAllPerson();
}