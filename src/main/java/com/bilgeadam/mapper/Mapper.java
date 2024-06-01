package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.FindAllResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.User;

import java.util.List;

public class Mapper {



    public  static User toUser(RegisterRequestDto dto){
        return User.builder()
                .name(dto.getName())
                .surName(dto.getSurName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    public static RegisterResponseDto toRegisterResponseDto(User user){

        return RegisterResponseDto.builder().id(user.getId()).email(user.getEmail()).build();
    }

    public static List<FindAllResponseDto> toFindAllResponseDto(List<User> users){

        return users.stream().map(x->FindAllResponseDto.builder()
                .id(x.getId())
                .email(x.getEmail())
                .phone(x.getPhone())
                .name(x.getName())
                .surName(x.getSurName())
                .comments(x.getComments())
                .favGenres(x.getFavGenres())
                .favMovies(x.getFavMovies())
                .userType(x.getUserType()).build()).toList();
    }
}
