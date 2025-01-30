package com.example.demo1.services;

import com.example.demo1.dtos.user.*;
import com.example.demo1.models.User;
import com.example.demo1.services.baseService.Addable;
import com.example.demo1.services.baseService.Findable;
import com.example.demo1.services.baseService.Modifiable;
import com.example.demo1.services.baseService.Removable;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface UserServices extends Findable<Long, User>, Addable<UserRegDto, User>,
        Modifiable<UserUpdateReqDto, User>, Removable<UserListDto>
{
    User login(UserLoginDto userLoginDto);

    UserRegDto register(UserRegDto userRegDto);

    UserProfile updateProfile(UserProfile userProfile);

    Optional<User> find(Long id, User user);

    User save(User user);


    @Transactional
    void getCvFileName(Long userId, String cvFileName);

    public void updateCv(User user, String fileName);

//    boolean changePassword(UserUpdateReqDto userUpdateReqDto);
}
