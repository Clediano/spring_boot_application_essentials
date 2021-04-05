package com.devdojo.springbootessentials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequestBody {

    @NotEmpty(message = "The name cannot be empty")
    private String name;
    @org.hibernate.validator.constraints.URL(message = "The URL is not valid")
    private String URL;
}
