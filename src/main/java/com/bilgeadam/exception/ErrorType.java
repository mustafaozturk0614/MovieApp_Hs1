package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatası",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4210,"Kullancı adı veye Şifre Hatalı !!!",HttpStatus.UNAUTHORIZED),
    UNEXPECTED_ERROR(4220,"Beklenmeyen Hata",HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(4211,"Veri Butunlüğü ihlali: Girdi verileri eksik veya geçersiz" ,HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS(4212,"Bu kullanıcı adi ile daha once kayıt olunmustur" ,HttpStatus.BAD_REQUEST);

    ;

     private int code;
     private String message;
     private HttpStatus httpStatus;


}
