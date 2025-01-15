package com.example.demo1.mappers;


import com.example.demo1.dtos.user.UserLoginDto;
import com.example.demo1.dtos.user.UserProfile;
import com.example.demo1.dtos.user.UserRegDto;
import com.example.demo1.dtos.user.UserUpdateReqDto;
import com.example.demo1.infrastructure.SimpleMapper;
import com.example.demo1.models.User;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper extends SimpleMapper<User,UserRegDto> {


    UserLoginDto toLoginDto(User user);

    User toEntity(UserRegDto userRegDto);

    UserRegDto toRegDto(User user);

    User toEntity(UserUpdateReqDto userUpdateReqDto);

    UserUpdateReqDto toUpdateDto(User user);

    UserProfile toProfileDto(User user);

    User toEntity(UserProfile userProfile);
}
