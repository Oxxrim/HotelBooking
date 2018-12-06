package ua.fift.kpi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String phoneNumber;

    public User(String username, String phoneNumber) {
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
}
