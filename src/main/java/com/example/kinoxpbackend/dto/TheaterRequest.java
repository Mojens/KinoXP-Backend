package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Theater;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheaterRequest {

    private int size;

    public static Theater getTheaterEntity(TheaterRequest t){
        return new Theater(t.size);
    }

    public TheaterRequest(Theater size) {
        this.size = size.getSize();
    }

}
