package com.example.demo1.services.impls;

import com.example.demo1.dtos.user.*;
import com.example.demo1.exceptions.EmailExistException;
import com.example.demo1.exceptions.UserNotFoundException;
import com.example.demo1.exceptions.UsernameExistsException;
import com.example.demo1.exceptions.WrongPasswordException;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import com.example.demo1.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserServices {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserLoginDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException("Wrong password! Try again!");
        }

        // Map the `User` entity to `UserLoginDto`
        return userMapper.toLoginDto(user);
    }


    @Override
    public UserRegDto register(UserRegDto userRegDto) {
        if (userRepository.findByUsername(userRegDto.getUsername()).isPresent()) {
            throw new UsernameExistsException("Username already exists");
        }

        User user = userMapper.toEntity(userRegDto);
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));

        var savedUser = userRepository.save(user);
        return userMapper.toRegDto(savedUser);
    }

    @Override
    public UserProfile updateProfile(UserProfile userProfile) {
        User user = userRepository.findByUsername(userProfile.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setName(userProfile.getName());
        user.setLastName(userProfile.getLastName());
        user.setPhone(userProfile.getPhone());
        user.setEmail(userProfile.getEmail());
        user.setBio(userProfile.getBio());

        return userMapper.toProfileDto(user);
    }

    @Override
    public UserProfile getProfileDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return userMapper.toProfileDto(user);
    }

    @Override
    public boolean changePassword(UserUpdateReqDto userUpdateReqDto) {
        var exist = userRepository.findByUsername(userUpdateReqDto.getUsername());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        var user = userMapper.toEntity(userUpdateReqDto);
        user.setPassword(passwordEncoder.encode(userUpdateReqDto.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public User add(UserRegDto userRegDto) {
        var user = userMapper.toEntity(userRegDto);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameExistsException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User find(UserListDto user) {
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User modify(UserUpdateReqDto userUpdateReqDto) {
        var exist = userRepository.findByUsername(userUpdateReqDto.getUsername());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        var user = userMapper.toEntity(userUpdateReqDto);
        return userRepository.save(user);
    }

    @Override
    public void remove(UserListDto user) {
        var exist = userRepository.findById(user.getId());
        if (exist.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(user.getId());
    }
}
