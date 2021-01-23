package com.example.demo.controller;


import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        String res = orderService.createOrder(order);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/additem/{orderId}")
    public ResponseEntity<String> addItem(@RequestBody Item item, @PathVariable("orderId") int orderId){
        String res = orderService.addItemIntoOrder(item, orderId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/viewitems/{orderId}")
    public ResponseEntity<List<Item>> getItemList(@PathVariable("orderId") int orderId){
        List<Item> itemList = orderService.getItemsFromOrder(orderId).getItemList();

        return  ResponseEntity.ok(itemList);
    }

    @DeleteMapping("/deleteitem/{orderId}/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable("orderId") int orderId, @PathVariable("itemId") int itemId){
        String res = orderService.deleteItemFromOrder(itemId ,orderId);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/updateitem/{orderId}/{itemId}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable("orderId") int orderId, @PathVariable("itemId") int itemId){

        Item item1 = orderService.updateItemIntoOrder(item, orderId, itemId);
        return  ResponseEntity.ok(item1);
    }
}
