package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode


@Entity
public class Reservation {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    @Column ( length = 50, nullable = false)
    String email;
    @Column ( length = 8, nullable = false)
    String phoneNumber;
    @Column ( nullable = false)
    int employeeId;
    @Column ( length = 16, nullable = false, unique = true)
    String safetyId;

    @ManyToOne
    Screening screening;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<SeatChoice> seatChoices = new ArrayList<>();

    // get booked seats from reservation
    public List<SeatChoice> getSeatChoices() {
        return seatChoices;
    }

    public Reservation(String email, String phoneNumber, int employeeId, String safetyId, Screening screening) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employeeId = employeeId;
        this.safetyId = safetyId;
        this.screening = screening;
    }

}
