package com.dota2.main.service;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super("Test A Nekosor");
    }

    public StorageException(String message, Throwable cause) {
        super("Test B Nekosor", cause);
    }
}