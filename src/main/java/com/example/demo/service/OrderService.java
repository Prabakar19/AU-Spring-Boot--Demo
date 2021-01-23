package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.Order;

public interface OrderService {

    public String createOrder(Order order);

    public String addItemIntoOrder(Item item, int orderId);

    public Order getItemsFromOrder(int OrderId);

    public String deleteItemFromOrder(int itemId, int orderId);

    public Item updateItemIntoOrder(Item item, int orderId, int itemId);
}
