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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    public User login(UserLoginDto userLoginDto) {
        // Fetch the User entity from the database
        User user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Verify the password
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new WrongPasswordException("Invalid password!");
        }

        return user; // Return the User object
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

        User existingUser = userRepository.findById(userProfile.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existingUser.setName(userProfile.getName());
        existingUser.setLastName(userProfile.getLastName());
        existingUser.setPhone(userProfile.getPhone());
        existingUser.setEmail(userProfile.getEmail());
        existingUser.setBio(userProfile.getBio());
        existingUser.setAddress(userProfile.getAddress());
        existingUser.setCity(userProfile.getCity());
        existingUser.setCountry(userProfile.getCountry());

        return userMapper.toProfileDto(userRepository.save(existingUser));
    }



//    @Override
//    public boolean changePassword(UserUpdateReqDto userUpdateReqDto) {
//        var exist = userRepository.findByUsername(userUpdateReqDto.getUsername());
//        if (exist.isEmpty()) {
//            throw new EntityNotFoundException("User not found");
//        }
//
//        var user = userMapper.toEntity(userUpdateReqDto);
//        user.setPassword(passwordEncoder.encode(userUpdateReqDto.getPassword()));
//        userRepository.save(user);
//
//        return true;
//    }

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
    public Optional<User> find(Long id, User user) {
        var exist = userRepository.findById(user.getId());
        if (exist.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return exist;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Transactional
    @Override
    public void getCvFileName(Long userId, String cvFileName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setCvFileName(cvFileName); // Update only the CV filename
        userRepository.save(user);
    }

    @Override
    public void updateCv(User user, String fileName) {
        user.setCvFileName(fileName);
        userRepository.save(user);
    }


    @Override
    public void remove(UserListDto user) {
        var exist = userRepository.findById(user.getId());
        if (exist.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(user.getId());
    }

    @Override
    public User modify(UserUpdateReqDto updateReqDto) {
        return null;
    }

    @Override
    public User find(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
