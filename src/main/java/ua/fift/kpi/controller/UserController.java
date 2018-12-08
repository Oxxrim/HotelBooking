package ua.fift.kpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.domain.User;
import ua.fift.kpi.repository.OrderRepository;
import ua.fift.kpi.repository.RoomRepository;
import ua.fift.kpi.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoomRepository roomRepository;

    private User usr;

    @PostMapping
    public String saveUser(@RequestBody User user){
        usr = new User(user.getUsername(), user.getPhoneNumber());

        userRepository.save(usr);

        return "user has been saved";
    }

    @PostMapping("/book/{roomNumber}")
    public String bookTheRoom(@RequestBody Order order, @PathVariable int roomNumber) throws ParseException {

        Room room = roomRepository.findByNumber(roomNumber);

        Calendar since = new GregorianCalendar();
        Calendar to = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Date date = sdf.parse(order.getSince());
        since.setTime(date);
        date = sdf.parse(order.getTo());
        to.setTime(date);

        int days = daysBetween(since.getTime(), to.getTime());
        long totalPrice = room.getPrice() * days;
        System.out.println(days + "\n" + totalPrice);
        if(order.isBreakfast()){
            totalPrice += room.getBreakfastCost() * days;
            System.out.println(totalPrice);
        }
        if(order.isCleaning()){
            totalPrice += room.getCleaningCost() * days;
            System.out.println(totalPrice);
        }

        order.setTotalPrice(totalPrice);
        order.setRoom(room);
        order.setClient(usr);

        orderRepository.save(order);

        return "Your order has been accepted";
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
