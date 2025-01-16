package com.example.demo1.services;

import com.example.demo1.dtos.user.*;
import com.example.demo1.models.User;
import com.example.demo1.services.baseService.Addable;
import com.example.demo1.services.baseService.Findable;
import com.example.demo1.services.baseService.Modifiable;
import com.example.demo1.services.baseService.Removable;

public interface UserServices extends Findable<UserListDto, User>, Addable<UserRegDto, User>,
        Modifiable<UserUpdateReqDto, User>, Removable<UserListDto>
{
    User login(UserLoginDto userLoginDto);

    UserRegDto register(UserRegDto userRegDto);

    User updateProfile(UserProfile userProfile);

    boolean changePassword(UserUpdateReqDto userUpdateReqDto);
}
