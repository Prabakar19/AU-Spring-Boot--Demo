package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Order {

    @NonNull
    private int orderId;

    @Max(20)
    private List<Item> itemList = new ArrayList<>();

}
