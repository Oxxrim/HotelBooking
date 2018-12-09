package ua.fift.kpi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Room {

    @Id
    private int number;

    private String category;

    private long price;

    private long breakfastCost;

    private long cleaningCost;

    public Room(long price, long breakfastCost, long cleaningCost) {
        this.price = price;
        this.breakfastCost = breakfastCost;
        this.cleaningCost = cleaningCost;
    }
}
