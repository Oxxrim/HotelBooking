package ua.fift.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.fift.kpi.domain.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    List<Room> findByCategory(String category);
}
