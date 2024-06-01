package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.FindAllResponseDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {


    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

   User toUser(RegisterRequestDto dto);
   RegisterResponseDto toRegisterResponseDto(User user);
   List<FindAllResponseDto> toFindAllResponseDtos(List<User> users);

   LoginResponseDto toLoginResponseDto(User user);

}
