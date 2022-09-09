package com.api.library.controller;

import com.api.library.auth.dto.UserResponseDto;
import com.api.library.auth.dto.UserUpdateDto;
import com.api.library.domain.util.Url;
import com.api.library.exception.BadRequestException;
import com.api.library.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Url.USER_URI)
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> usersDTOs = this.userService.getAllUsers();
        return ResponseEntity.ok().body(usersDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long userId) {
        this.userService.deleteUserById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("users/{id}")
    public UserResponseDto updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return userService.updateUser(userId, userUpdateDto);
    }

}
