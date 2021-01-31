package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
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

    public Item(int itemId, @NonNull @Size(min = 1, max = 50) String itemName, String description, @Max(100) int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
    }
}
