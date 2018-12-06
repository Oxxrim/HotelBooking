package ua.fift.kpi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.fift.kpi.domain.Room;
import ua.fift.kpi.repository.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/{category}")
    public List<Room> filterByCategory(@PathVariable String category){
        List<Room> rooms;
        rooms = roomRepository.findByCategory(category);

        return rooms;
    }
}
