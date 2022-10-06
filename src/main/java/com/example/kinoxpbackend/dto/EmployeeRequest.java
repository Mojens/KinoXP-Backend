package com.example.kinoxpbackend.dto;


import com.example.kinoxpbackend.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeRequest {

  private int id;

  private String name;

  private String password;

  private String userName;

  private int type;

  public static Employee getEmployeeEntity(EmployeeRequest employeeRequest){
    return Employee.builder()
        .id(employeeRequest.id)
        .name(employeeRequest.name)
        .password(employeeRequest.password)
        .userName(employeeRequest.userName)
        .type(employeeRequest.type)
        .build();
  }

  public EmployeeRequest(Employee e) {
    this.id = e.getId();
    this.name = e.getName();
    this.password = e.getPassword();
    this.userName = e.getUserName();
    this.type = e.getType();
  }
}
