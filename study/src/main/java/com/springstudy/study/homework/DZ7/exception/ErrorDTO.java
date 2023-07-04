package com.springstudy.study.homework.DZ7.exception;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDTO {
    private final String message;
    private final String description;
    private List<FieldErrorDTO> fieldErrors;

    public ErrorDTO(String message) {
        this(message, null);
    }

    public ErrorDTO(String message,
                    String description) {
        this.message = message;
        this.description = description;
    }

    public void add(String objectName,
                    String field,
                    String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDTO(objectName, field, message));

    }
}

