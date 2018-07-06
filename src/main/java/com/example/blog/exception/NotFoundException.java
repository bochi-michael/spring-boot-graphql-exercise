package com.example.blog.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class clazz) {
        super(clazz.getName() + " not found");
    }
}
