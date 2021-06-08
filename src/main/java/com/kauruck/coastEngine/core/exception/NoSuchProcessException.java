package com.kauruck.coastEngine.core.exception;

public class NoSuchProcessException extends Exception{

    public NoSuchProcessException(int pid){
        super("There is no process: " + pid);
    }
}
