package ua.fift.kpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.domain.User;
import ua.fift.kpi.repository.OrderRepository;
import ua.fift.kpi.repository.RoomRepository;
import ua.fift.kpi.repository.UserRepository;
import ua.fift.kpi.service.ControllerService;

import java.text.ParseException;
import java.util.List;

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

        ControllerService service = new ControllerService();

        Room room = roomRepository.findByNumber(roomNumber);

        if (service.checkForUnavailable(order, orderRepository).contains(room)){
            return "Sorry, but this room is not available";
        } else {
            order.setTotalPrice(service.countTheTotalPrice(order, room));
            order.setRoom(room);
            order.setClient(usr);

            orderRepository.save(order);

            return "Your order has been accepted";
        }
    }

    @DeleteMapping("/book/{bookId}")
    public String deleteTheBook(@PathVariable int bookId){
        orderRepository.deleteById(bookId);
        return "The book was deleted";
    }

    @PutMapping("/book/{bookId}/{roomNumber}")
    public String updateTheBook(@RequestBody Order order, @PathVariable int bookId, @PathVariable int roomNumber) throws ParseException {
        order.setId(bookId);
        ControllerService service = new ControllerService();

        Room room = roomRepository.findByNumber(roomNumber);

        if (service.checkForUnavailable(order, orderRepository).contains(room)){
            return "Sorry, but this room is not available";
        } else {
            order.setTotalPrice(service.countTheTotalPrice(order, room));
            order.setRoom(room);
            order.setClient(usr);

            orderRepository.save(order);

            return "Your book has been updated";
        }
    }

    @GetMapping("/bookings")
    public List<Order> viewBookings(){
        return orderRepository.findByClient(usr);
    }

    @GetMapping("/bookings/{bookId}")
    public long viewTotalPrice(@PathVariable int bookId){
        return orderRepository.findByIdAndClient(bookId, usr).getTotalPrice();
    }


}
