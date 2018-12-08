package ua.fift.kpi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.repository.OrderRepository;
import ua.fift.kpi.repository.RoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<Room> rooms = roomRepository.findByCategory(category);

        return rooms;
    }

    @PostMapping("/available")
    public List<Room> filterByDate(@RequestBody Order order) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        List<Room> rooms = (List<Room>) roomRepository.findAll();
        Iterable<Order> orders = orderRepository.findAll();

        List<Room> unavailable = new ArrayList<>();
        List<Room> available = new ArrayList<>();

        for (Order ordr : orders) {
            Date dateSince1 = sdf.parse(order.getSince());
            Date dateSince2 = sdf.parse(ordr.getSince());
            Date dateTo1 = sdf.parse(order.getTo());
            Date dateTo2 = sdf.parse(ordr.getTo());

            System.out.println(dateSince1.after(dateSince2));
            System.out.println(dateSince1.before(dateTo2));
            System.out.println(dateTo1.after(dateSince2));
            System.out.println(dateTo1.before(dateTo2));
            if (dateSince1.after(dateSince2) && dateSince1.before(dateTo2)){
                unavailable.add(ordr.getRoom());
            } else if (dateTo1.after(dateSince2) && dateTo1.before(dateTo2)){
                unavailable.add(ordr.getRoom());
            }
        }

        System.out.println(unavailable.size());

        System.out.println(rooms.size());


            for (Room room : unavailable) {
                rooms.remove(room);
            }



        System.out.println(available.size());

        return rooms;
    }
}
