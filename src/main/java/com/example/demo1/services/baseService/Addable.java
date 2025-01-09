package com.example.demo1.services.baseService;
@FunctionalInterface
public interface Addable<T,R> {
    R add(T t);
}
