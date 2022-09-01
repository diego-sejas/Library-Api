package com.api.LibraryApi.domain.service;

import com.api.LibraryApi.auth.dto.UserResponseDto;
import com.api.LibraryApi.auth.dto.UserUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IUserService {

    @Transactional(readOnly = true)
    List<UserResponseDto> getAllUsers();

    @Transactional()
    void deleteUserById(Long userId);

    @Transactional()
    UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto);
}
