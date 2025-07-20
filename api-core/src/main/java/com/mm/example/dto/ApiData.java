package com.mm.example.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiData {

    private Long id;
    private String staffNumber;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String position;
    private String division;
    private String team;

}
