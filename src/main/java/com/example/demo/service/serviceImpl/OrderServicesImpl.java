package com.example.demo.service.serviceImpl;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServicesImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServicesImpl.class);

    private List<Order> orderList = new ArrayList<>();


    private boolean isOrderDuplicate(int orderId) {
        for(Order order : orderList)
            if(order.getOrderId() == orderId)
                return true;
        return false;
    }
    private boolean isItemDuplicate(int itemId, int orderIndex) {
        for(Item item : orderList.get(orderIndex).getItemList())
            if(item.getItemId() == itemId)
                return true;
        return false;
    }

    @Override
    public String createOrder(Order order) {
        if(!isOrderDuplicate(order.getOrderId())) {
            orderList.add(order);
            logger.info("Order created successfully!");
            return "Order Successfully Created!";
        }
        logger.warn("Order is duplicate");
        return "Order is duplicate!";
    }

    @Override
    public String addItemIntoOrder(Item item, int orderId) {

        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent()) {
            logger.error("Order Not Found !!");
            return "Order Not Found !!";
        }

        int orderIndex = orderList.indexOf(optionalOrder.get());
        orderList.get(orderIndex).getItemList().add(item);
        logger.info("Item added successfully to order!");
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

        if(!optionalOrder.isPresent()) {
            logger.warn("Order Not Found !!");
            return "Order Not Found !!";
        }

        List<Item> itemList  = optionalOrder.get().getItemList().stream().filter(item -> itemId != item.getItemId()).collect(Collectors.toList());
        optionalOrder.get().setItemList(itemList);
        logger.info("Item deleted!!");
        return "Item deleted!!";
    }

    @Override
    public Item updateItemIntoOrder(Item item, int orderId, int itemId) {
        Optional<Order> optionalOrder = orderList.stream().filter(order -> orderId == order.getOrderId()).findFirst();

        if(!optionalOrder.isPresent()) {
         logger.warn("order is not present!!");
            return null;
        }

        Optional<Item> optionalItem = optionalOrder.get().getItemList().stream().filter(item1 -> itemId == item1.getItemId()).findFirst();

        if(!optionalItem.isPresent()) {
            logger.warn("item is not present!!");
            return null;
        }

        Item modifiedItem = optionalItem.get();

        modifiedItem.setItemName(item.getItemName());
        modifiedItem.setDescription(item.getDescription());
        modifiedItem.setQuantity(item.getQuantity());
        logger.info("Item updated");
        return modifiedItem;
    }
}
