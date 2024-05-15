package com.example.Controller.DB.ManyToOne;

import com.example.Entity.DB.ManyToOne.Order;
import com.example.Service.DB.ManyToOne.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value="/add-order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order o = orderService.createOrder(order);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @GetMapping(value="/getone-order/{id}")
    public ResponseEntity<Order> getOne(@PathVariable long id){
        Order order = orderService.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value="/getall-orders")
    public ResponseEntity<List<Order>> getallOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
