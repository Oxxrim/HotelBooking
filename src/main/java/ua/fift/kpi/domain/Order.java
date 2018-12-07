package ua.fift.kpi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean breakfast;

    private boolean cleaning;

    private String since;

    private String to;

    private long totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    @OneToOne
    @JoinColumn(name = "room_number")
    private Room room;

    public Order(boolean breakfast, boolean cleaning, String since, String to, long totalPrice, User client, Room room) {
        this.breakfast = breakfast;
        this.cleaning = cleaning;
        this.since = since;
        this.to = to;
        this.totalPrice = totalPrice;
        this.client = client;
        this.room = room;
    }

}
