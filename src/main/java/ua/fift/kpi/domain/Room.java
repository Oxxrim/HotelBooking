package ua.fift.kpi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Room {

    @Id
    private int number;

    private String category;

    private long price;

    private long breakfastCost;

    private long cleaningCost;
}
