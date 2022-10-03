package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Theater {
    @Id
    private int size;

    @OneToMany(mappedBy = "theater")
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "theater")
    private List<Seat> seat = new ArrayList<>();


    public Theater() {
    }

}
