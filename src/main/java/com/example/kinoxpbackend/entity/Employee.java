package com.example.kinoxpbackend.entity;


import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String userName;

  @Column(nullable = false)
  private int type;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Shift> shifts = new ArrayList<>();

  public void addShift(Shift shift){
    shifts.add(shift);
    shift.setEmployee(this);
  }

  //Hash pwd on Lombok builder
  public static class EmployeeBuilder{
    public EmployeeBuilder password(String password){
      this.password = BCrypt.hashpw(password,BCrypt.gensalt());
      return this;
    }
  }

}

