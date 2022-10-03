package com.example.kinoxpbackend;

import com.example.kinoxpbackend.configuration.ReservationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
public class KinoXpBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(KinoXpBackendApplication.class, args);
  }

}
