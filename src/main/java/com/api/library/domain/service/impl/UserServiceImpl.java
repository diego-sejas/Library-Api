package com.api.library.domain.service.impl;

import com.api.library.auth.dto.UserResponseDto;
import com.api.library.auth.dto.UserUpdateDto;
import com.api.library.auth.repository.UserRepository;
import com.api.library.domain.model.UserEntity;
import com.api.library.domain.service.IUserService;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.UserMapper;

import com.amazonaws.services.sns.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> entities = this.userRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<UserResponseDto> userResponseDtos = userMapper.userEntityList2ResponseDtoList(entities);
        return userResponseDtos;
    }

    @Override
    @Transactional()
    public void deleteUserById(Long userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("the user does not exist or has already been deleted");
        }
        user.get().setDeleted(true);
        userRepository.save(user.get());
    }

    @Override
    @Transactional()
    public UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto) {
        UserEntity user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User with id = " + userId + " was not found"));
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        UserEntity updatedUser = userRepository.save(user);

        return userMapper.userEntity2ResponseDto(updatedUser);
    }


}
