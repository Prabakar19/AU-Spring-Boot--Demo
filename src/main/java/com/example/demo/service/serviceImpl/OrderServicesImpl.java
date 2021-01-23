package com.example.demo.service.serviceImpl;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServicesImpl implements OrderService {

    private List<Order> orderList = new ArrayList<>();

    @Override
    public String createOrder(Order order) {
        orderList.add(order);
        return "Order Successfully Created!";
    }

    @Override
    public String addItemIntoOrder(Item item, int orderId) {

        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent())
            return "Order Not Found !!";

        int orderIndex = orderList.indexOf(optionalOrder.get());
        orderList.get(orderIndex).getItemList().add(item);

        return "Item Added Successfully";
    }

    @Override
    public Order getItemsFromOrder(int orderId) {
        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent())
            return null;

        return optionalOrder.get();
    }

    @Override
    public String deleteItemFromOrder(int itemId, int orderId) {
        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent())
            return "Order Not Found !!";

        List<Item> itemList  = optionalOrder.get().getItemList().stream().filter(item -> itemId != item.getItemId()).collect(Collectors.toList());
        optionalOrder.get().setItemList(itemList);

        return "Item deleted!!";
    }

    @Override
    public Item updateItemIntoOrder(Item item, int orderId, int itemId) {
        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent())
            return null;

        Optional<Item> optionalItem = optionalOrder.get().getItemList().stream().filter(item1 -> itemId == item1.getItemId()).findFirst();

        if(!optionalItem.isPresent())
            return null;

        Item modifiedItem = optionalItem.get();

        modifiedItem.setItemName(item.getItemName());
        modifiedItem.setDescription(item.getDescription());
        modifiedItem.setQuantity(item.getQuantity());

        return modifiedItem;
    }
}
