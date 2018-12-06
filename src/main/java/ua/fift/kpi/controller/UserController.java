package ua.fift.kpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.fift.kpi.domain.User;
import ua.fift.kpi.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private User usr;

    @PostMapping
    public String saveUser(@RequestBody User user){
        usr = new User(user.getUsername(), user.getPhoneNumber());

        userRepository.save(usr);

        return "user has been saved";
    }
}
