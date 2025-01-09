package com.example.demo1.services.baseService;
@FunctionalInterface
public interface Findable <T, R> {
    R find(T id);
}
