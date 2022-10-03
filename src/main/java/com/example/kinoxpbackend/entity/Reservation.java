package com.example.kinoxpbackend.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column ( length = 255, nullable = false)
    String email;
    @Column ( length = 8, nullable = false)
    String phoneNumber;
    @Column ( nullable = false)
    int employeeId;
    @Column ( length = 16, nullable = false, unique = true)
    String safetyId;


    public Reservation(String email, String phoneNumber, int employeeId, String safetyId) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employeeId = employeeId;
        this.safetyId = safetyId;
    }
}
