package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Theater {
    @Id
    private int id;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Seatings> seatings = new ArrayList<>();


    // get number of seats in theater


    public Theater() {
    }

    public Theater(int id) {
        this.id = id;
    }
}
