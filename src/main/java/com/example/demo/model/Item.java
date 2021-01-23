package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Setter
@Getter
public class Item {

    @NonNull
    private int itemId;

    @NonNull
    @Size(min = 1, max = 50)
    private String itemName;

    private String description;

    @NonNull
    @Max(100)
    private int quantity;
}
