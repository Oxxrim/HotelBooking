package ua.fift.kpi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.repository.OrderRepository;
import ua.fift.kpi.repository.RoomRepository;
import ua.fift.kpi.service.ControllerService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{category}")
    public List<Room> filterByCategory(@PathVariable String category){
        return roomRepository.findByCategory(category);
    }

    @PostMapping("/available")
    public List<Room> filterByDate(@RequestBody Order order) throws ParseException {
        List<Room> rooms = (List<Room>) roomRepository.findAll();

        ControllerService service = new ControllerService();

        List<Room> unavailable = service.checkForAvailable(order, orderRepository);

        for (Room room : unavailable) {
            rooms.remove(room);
        }

        return rooms;
    }
}
