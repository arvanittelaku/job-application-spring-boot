package com.example.demo1.services.impls;

import com.example.demo1.dtos.user.UserListDto;
import com.example.demo1.dtos.user.UserLoginDto;
import com.example.demo1.dtos.user.UserRegDto;
import com.example.demo1.dtos.user.UserUpdateReqDto;
import com.example.demo1.exceptions.EmailExistException;
import com.example.demo1.exceptions.UserNotFoundException;
import com.example.demo1.exceptions.UsernameExistsException;
import com.example.demo1.mappers.UserMapper;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import com.example.demo1.services.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
    public User login(String username, String password) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new EntityNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new EntityNotFoundException("User not found");
        }
        userMapper.fromLogin(user);
        return user.get();
    }


    @Override
    public boolean changePassword(UserUpdateReqDto userUpdateReqDto) {
        var exist = userRepository.findByUsername(userUpdateReqDto.getUsername());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        var user = userMapper.fromUpdateReqDto(userUpdateReqDto);
        user.setPassword(passwordEncoder.encode(userUpdateReqDto.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public User add(UserRegDto userRegDto) {
        var user = userMapper.fromUserRegDtoToEntity(userRegDto);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameExistsException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        var savedUser = userRepository.save(user);
        return savedUser;
    }


    @Override
    public User find(UserListDto user) {
        var exist = userRepository.findById(user.getId());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        return exist.get();
    }

    @Override
    public User modify(UserUpdateReqDto userUpdateReqDto) {
        var exist = userRepository.findByUsername(userUpdateReqDto.getUsername());
        if (exist.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        var user = userMapper.fromUpdateReqDto(userUpdateReqDto);
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
