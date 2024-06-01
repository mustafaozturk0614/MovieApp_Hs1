package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequestDto {
    private  String name;
    private String surName;
    private String email;
    private String password;


}


