package com.bruce.services.user_service.services;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.dto.UserDto;
import com.bruce.services.user_service.exception.UserNotFoundException;
import java.util.List;
import java.util.UUID;

public interface IUser {

    // Retrieve
    List<User> getUser();
    User getUserById(UUID id) throws UserNotFoundException;
    List<User> getUserByPagination(int pageNumber, int pageSize);

    // Add
    User addUser(UserDto userDto);

    // Update
    User updateUser(UUID id, UserDto userDto) throws UserNotFoundException;

    // Delete
    void deleteUser(UUID id);

}
