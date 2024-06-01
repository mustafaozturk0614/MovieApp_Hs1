package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequestDto {
    @NotBlank(message = "boş isim girilemez")
    private  String name;
    @NotBlank
    private String surName;
    @Email(message = "email formatı hatalı")
    private String email;
    @Size(min = 5,max = 32,message = "Şifre 5 ile 32 karakter arasında olmalıdır")
    private String password;


}


