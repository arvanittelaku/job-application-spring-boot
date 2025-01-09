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


    User fromLogin (Optional<User> userLoginDto);

    UserLoginDto fromUser (User user);

    User fromUserRegDtoToEntity (UserRegDto userRegDto);

    UserRegDto fromUserToReg (User user);

    User fromUpdateReqDto (UserUpdateReqDto userUpdateReqDto);

    UserUpdateReqDto fromUserToUpdate (User user);

    User fromUserProfile (UserProfile userProfile);

    UserProfile fromUserToProfile (User user);

    UserProfile fromUserToUpdateProfile (User user);

    User fromUpdateProfile (UserUpdateReqDto userUpdateReqDto);



}
