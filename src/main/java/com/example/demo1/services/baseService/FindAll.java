package com.example.demo1.services.baseService;

import java.util.List;
@FunctionalInterface
public interface FindAll <T>{
    List<T> findAll();
}
