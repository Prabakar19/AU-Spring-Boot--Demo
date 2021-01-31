package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    void createOrderTest(){
        Item item1 = new Item(1, "Test item 1", "description", 3);
        Item item2 = new Item(2, "Test item 2", "description", 5);
        Item item3 = new Item(2, "Test item 3", "description", 4);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        Order order = new Order();
        order.setOrderId(1);
        order.setItemList(itemList);

        Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn("Order Successfully Created!");
        ResponseEntity<String> response = orderController.createOrder(order);
        Assertions.assertEquals("Order Successfully Created!", response.getBody());

        Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn("Order is duplicate!");
        ResponseEntity<String> duplicateResponse = orderController.createOrder(order);
        Assertions.assertEquals("Order is duplicate!", duplicateResponse.getBody());
    }

    @Test
    void addItemTest(){
        Mockito.when(orderService.addItemIntoOrder(Mockito.any(Item.class), Mockito.anyInt())).thenReturn("Item Added Successfully");
        Item item = new Item(1, "Test item 1", "description", 5);
        ResponseEntity<String> successResponse = orderController.addItem(item, 1);
        Assertions.assertEquals("Item Added Successfully", successResponse.getBody());

        Mockito.when(orderService.addItemIntoOrder(Mockito.any(Item.class), Mockito.anyInt())).thenReturn("Order Not Found !!");
        ResponseEntity<String> orderNotFoundResponse = orderController.addItem(item, 1);
        Assertions.assertEquals("Order Not Found !!", orderNotFoundResponse.getBody());

    }

    @Test
    void getItemListTest(){
        List<Item> itemList = new ArrayList<>();
        Order order = new Order();
        Mockito.when(orderService.getItemsFromOrder(Mockito.anyInt())).thenReturn(order);
        ResponseEntity<List<Item>> listResponseEntity = orderController.getItemList(1);
        Assertions.assertEquals(itemList, listResponseEntity.getBody());
    }

    @Test
    void deleteItemTest() {
        Mockito.when(orderService.deleteItemFromOrder(Mockito.anyInt(), Mockito.anyInt())).thenReturn("Item deleted!!");
        ResponseEntity<String> successResponse = orderController.deleteItem(1, 1);
        Assertions.assertEquals("Item deleted!!", successResponse.getBody());

        Mockito.when(orderService.deleteItemFromOrder(Mockito.anyInt(), Mockito.anyInt())).thenReturn("Order Not Found !!");
        ResponseEntity<String> orderNotFoundResponse = orderController.deleteItem(1, 1);
        Assertions.assertEquals("Order Not Found !!", orderNotFoundResponse.getBody());
    }


    @Test
    void updateItemTest(){
        Item item = new Item();
        Mockito.when(orderService.updateItemIntoOrder(Mockito.any(Item.class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(item);
        ResponseEntity<Item> itemResponseEntity = orderController.updateItem(item,1,1);
        Assertions.assertEquals(item, itemResponseEntity.getBody());

        Mockito.when(orderService.updateItemIntoOrder(Mockito.any(Item.class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
        ResponseEntity<Item> itemNullResponseEntity = orderController.updateItem(item,1,1);
        Assertions.assertEquals(null, itemNullResponseEntity.getBody());
    }

}