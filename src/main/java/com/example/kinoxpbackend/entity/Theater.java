package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Theater {
    @Id
    private int id;

    @OneToMany(mappedBy = "theater")
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "theater")
    private List<Seat> seat = new ArrayList<>();


    public Theater() {
    }

    public Theater(int size) {
        this.id = size;
    }

}
