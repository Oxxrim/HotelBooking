package ua.fift.kpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/bookings")
    public List<Order> viewAllBookings(){

        return (List<Order>) orderRepository.findAll();
    }
}
