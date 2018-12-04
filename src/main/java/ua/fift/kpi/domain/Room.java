package ua.fift.kpi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue
    private int number;

    private String category;

    private long priceForNight;

    private boolean available;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    @JoinColumn(name = "name")
    private Set<AdditionalOptions> additionalOptions;


}
