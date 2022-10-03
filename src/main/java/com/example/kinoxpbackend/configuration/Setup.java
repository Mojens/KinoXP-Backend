package com.example.kinoxpbackend.configuration;


import com.example.kinoxpbackend.entity.Seat;
import com.example.kinoxpbackend.entity.Theater;
import com.example.kinoxpbackend.repository.SeatRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class Setup implements ApplicationRunner {

  SeatRepository seatRepository;

  public Setup(SeatRepository seatRepository){
    this.seatRepository = seatRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {


    seatConfig();
  }

  public static void seatConfig(){
    Theater theater1 = new Theater(1);
    Theater theater2 = new Theater(2);
    ArrayList<Seat> seatsTheater1 = new ArrayList<Seat>();
    String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
    for (int i = 0; i < 20; i++) {
      String row = rows[i];
      for (int j = 1; j <= 12; j++) {
        Seat tempSeat = new Seat(row, j, theater1);
        seatsTheater1.add(tempSeat);
      }
    }

    ArrayList<Seat> seatsTheater2 = new ArrayList<Seat>();
    for (int i = 0; i < 25; i++) {
      String row = rows[i];
      for (int j = 1; j <= 16; j++) {
        Seat tempSeat = new Seat(row, j, theater2);
        seatsTheater2.add(tempSeat);
      }
    }
    System.out.println(seatsTheater1);
    System.out.println(seatsTheater2);


  }

  /*
    @Override
    public void run(ApplicationArguments args){
        ArrayList<Reservation> reservations = new ArrayList<Reservation>(
                Arrays.asList(
                        new Reservation("nfnfjd@lddjk.dk","30208430", 1, "123456789"),
                        new Reservation("nfcdefjd@lddjk.dk","30205430", 5, "12345dew6789"),
                        new Reservation("frefwe@lddjk.dk","30328430", 4, "dfwedwed")
                )
        );
        reservationRepository.saveAll(reservations);
    }
    */

}
