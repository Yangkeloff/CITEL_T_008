package com.example.management.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddDao {
    void insert(String data);
}
