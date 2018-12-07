package ua.fift.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.fift.kpi.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
