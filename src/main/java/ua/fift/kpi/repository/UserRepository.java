package ua.fift.kpi.repository;

import org.springframework.data.repository.CrudRepository;
import ua.fift.kpi.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
