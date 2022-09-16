package com.alkemy.disneyApi.exception;

public class ParamNotFound extends RuntimeException {
    public ParamNotFound(String error) {
        super(error);
    }
}
