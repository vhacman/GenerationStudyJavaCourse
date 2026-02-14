package com.generation.javaeat.model.entities;

import java.util.List;

public interface Validable
{
    List<String> getErrors();

    default boolean isValid()
    {
        return getErrors().isEmpty();
    }

    default boolean emailIsValid(String email)
    {
        return email != null
            && email.matches("[\\w.-]+@[\\w.-]+\\.\\w{2,}")
            && !email.isEmpty();
    }
}
