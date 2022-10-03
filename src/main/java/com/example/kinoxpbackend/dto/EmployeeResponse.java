package com.example.kinoxpbackend.dto;

import com.example.kinoxpbackend.entity.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

  private int id;

  private String name;

  private String password;

  private String userName;

  private int type;

  public EmployeeResponse(Employee e, boolean includeAll){
    this.name = e.getName();
    this.userName = e.getUserName();

    if (includeAll){
      this.id = e.getId();
      this.password = e.getPassword();
      this.type = e.getType();
    }

  }

}
