package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Theater {
    @Id
    private int id;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Seatings> seatings = new ArrayList<>();


    public Theater() {
    }

    public Theater(int id) {
        this.id = id;
    }
}
