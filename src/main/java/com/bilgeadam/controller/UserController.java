package com.bilgeadam.controller;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.FindAllResponseDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    http metotları
    Get
    Post
    Put
    Delete


 */
//http://localhost:8090/
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    //http://localhost:8090/user
    private final UserService userService;


    @GetMapping()
    public String hello(){
        return "Hello";
    }

    @GetMapping("/name")
    public String getName(){
        return "Mustafa";
    }

    @GetMapping("/name-and-surname")
    public String getNameAndSurname(String name, String surname){
        return name+"-"+surname;
    }



    /*
        register metodu yazalım  ==> gerekli parametreler name surname password email
     */
    @GetMapping("/register")
    public ResponseEntity<User> register(String name, String surName, String password, String email) {
        return ResponseEntity.ok( userService.register(name, surName, password, email));
    }

    @GetMapping("/register2")
    public ResponseEntity<RegisterResponseDto> register2(RegisterRequestDto dto) {
        return ResponseEntity.ok( userService.register4(dto));
    }
    @PostMapping("/register3")
    public ResponseEntity<RegisterResponseDto> register3(@RequestBody RegisterRequestDto dto) {
        return ResponseEntity.ok( userService.register4(dto));
    }

    /*
        emaile gore kullanıcı getiren end point
     */
    @GetMapping("find-by-email")
    public ResponseEntity<User> findByEmail(String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(String email, String password) {
        Boolean check=userService.login(email, password);
        if (!check) {
            return ResponseEntity.badRequest().body("Kullanıcı adı veya şifre hatalı");
        }
        return ResponseEntity.ok(check);
    }
    /*
    login2 metodu bir request dto yaratıp dısarıdan verileri alalım
    ve response dto ise şu verileri donelim id si ile message donelim
    message==> login basarılı ise
     giriş başarılı değil ise kullancı adı veya şifre hatalı olsun

     */

    @GetMapping("/login2")
    public ResponseEntity<LoginResponseDto> login2(LoginRequestDto dto){
        return  ResponseEntity.ok( userService.login2(dto));
    }

    @GetMapping("/find-all-by-order-by-name")
    public ResponseEntity<List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }
    @GetMapping("/find-all-by-name-contains")
    public ResponseEntity<List<User>> findAllByNameContainsIgnoreCase(String value){
        return ResponseEntity.ok( userService.findAllByNameContainsIgnoreCase(value));
    }
    @GetMapping("/find-all-by-email-contains")
    public ResponseEntity<List<User>> findAllByEmailContainsIgnoreCase(String value){
        return ResponseEntity.ok( userService.findAllByEmailContainsIgnoreCase(value));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<FindAllResponseDto>> findAll(){
        return ResponseEntity.ok( userService.findAll2());
    }



}
