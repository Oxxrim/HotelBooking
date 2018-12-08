package ua.fift.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.fift.kpi.domain.Order;
import ua.fift.kpi.domain.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByClient(User user);

}
