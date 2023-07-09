package com.example.matei.controller;

import com.example.matei.entity.Order;
import com.example.matei.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    // handler method to handle list orders and return mode and view
    @GetMapping("/orders")
    public String listOrders(Model model) {
        System.out.println("try to access Orders !!!");
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/orders/new")
    public String createOrderForm(Model model) {

        // create order object to hold order form data
        Order order = new Order();
        model.addAttribute("order", order);
        return "create_order";

    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "edit_order";
    }

    @PostMapping("/orders/{id}")
    public String updateOrder(@PathVariable Long id,
                                @ModelAttribute("order") Order order,
                                Model model) {

        // get order from database by id
        Order existingOrder = orderService.getOrderById(id);
        existingOrder.setId(id);
        existingOrder.setFirstName(order.getFirstName());
        existingOrder.setLastName(order.getLastName());
        existingOrder.setEmail(order.getEmail());

        // save updated order object
        orderService.updateOrder(existingOrder);
        return "redirect:/orders";
    }

    // handler method to handle delete order request

    @GetMapping("/orders/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }

}
