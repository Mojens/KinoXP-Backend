package com.example.kinoxpbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class KinoXpBackendApplicationTests {

  @Test
  void contextLoads() {
    assertEquals(1, 1);
  }



}
