package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.FindAllResponseDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.MovieAppException;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.mapper.Mapper;
import com.bilgeadam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private  final UserRepository userRepository;

    public User register(String name, String surName, String password, String email) {
      User user=User.builder()
        .name(name)
        .surName(surName)
        .password(password)
        .email(email)
        .build();

    return userRepository.save(user);
    }

    //responseDto donsun artık
    public RegisterResponseDto register2(RegisterRequestDto dto) {
        User user=User.builder()
                .name(dto.getName())
                .surName(dto.getSurName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
           userRepository.save(user);
        return  RegisterResponseDto.builder().id(user.getId()).email(user.getEmail()).build();
    }
    public RegisterResponseDto register3(RegisterRequestDto dto) {
        User user= Mapper.toUser(dto);
        userRepository.save(user);
        return  Mapper.toRegisterResponseDto(user);
        //return  Mapper.toRegisterResponseDto(userRepository.save(Mapper.toUser(dto)));
    }

    public RegisterResponseDto register4(RegisterRequestDto dto) {
        Boolean checkEmail=userRepository.existsByEmail(dto.getEmail());
        if (checkEmail){
            throw  new MovieAppException(ErrorType.USERNAME_ALREADY_EXISTS);
        }

        User user= IUserMapper.INSTANCE.toUser(dto);
        userRepository.save(user);
        return  IUserMapper.INSTANCE.toRegisterResponseDto(user);
        //return  Mapper.toRegisterResponseDto(userRepository.save(Mapper.toUser(dto)));
    }

    public User findByEmail(String email){
        Optional<User> user= userRepository.findOptionalByEmail(email);
        if (user.isEmpty()){
            throw  new RuntimeException("Kullanıcı bulunamadı");
        }
        return user.get();
    }

    public Boolean login(String email, String password) {

       return userRepository.existsByEmailAndPassword(email, password);
    }

    public List<User> findAllByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<User> findAllByNameContainsIgnoreCase(String value){
        return userRepository.findAllByNameContainsIgnoreCase(value);
    }
    public List<User> findAllByEmailContainsIgnoreCase(String value){
        return userRepository.findAllByEmailContainsIgnoreCase(value);
    }

    public List<FindAllResponseDto> findAll() {
     List<User> users= userRepository.findAll();
     return Mapper.toFindAllResponseDto(users);
    }
    public List<FindAllResponseDto> findAll2() {
        List<User> users= userRepository.findAll();
        return IUserMapper.INSTANCE.toFindAllResponseDtos(users);
    }

    public LoginResponseDto login2(LoginRequestDto dto) {
        Optional<User> user= userRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        LoginResponseDto loginResponseDto;
        if(user.isEmpty()){
                throw  new MovieAppException(ErrorType.LOGIN_ERROR);
        }else {
            loginResponseDto = IUserMapper.INSTANCE.toLoginResponseDto(user.get());
            loginResponseDto.setMessage("Giriş Başarılı");
        }
       return loginResponseDto;
    }
}
