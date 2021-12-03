package com.kauruck.coastEngine.core.exception;

public class NoHandlerException extends Exception{
    public NoHandlerException(String className) {
        super("There is no handler for the class " + className + " registered");
    }
}
