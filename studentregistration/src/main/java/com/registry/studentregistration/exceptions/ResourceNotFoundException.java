package com.registry.studentregistration.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }
}
