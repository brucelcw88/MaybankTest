package com.bruce.services.user_service.services.impl;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.dto.UserDto;
import com.bruce.services.user_service.exception.UserNotFoundException;
import com.bruce.services.user_service.logging.ApiLogger;
import com.bruce.services.user_service.mapper.UserMapper;
import com.bruce.services.user_service.repository.UserRepository;
import com.bruce.services.user_service.services.IUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApiLogger apiLogger;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUser() {
        List<User> userList = Optional.ofNullable(userRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(u -> userMapper.toFormattedTimestamp(u))
                .collect(Collectors.toList());

        String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        apiLogger.logApiCall(url, "GET", null);

        log.info("url: {}", url);
        log.info("apilogger: {}", apiLogger.logApiCall(url, "GET", null));
        return userList;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(UUID id) throws UserNotFoundException {
        // Use optional to check null
        //        Optional<User> optionalUser = userRepository.findById(id);
        //        optionalUser.isPresent()...;
        //                or
        //        optionalUser.orElseThrow();
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User [" + id + "] not found in database."));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUserByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.getContent();
    }

    @Transactional
    @Override
    public User addUser(UserDto userDto) {
        // Include mapper
        User savedUser = null;
        try {
            savedUser = userMapper.toUserEntity(userDto);
            userRepository.save(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedUser;
    }

    @Transactional
    @Override
    public User updateUser(UUID id, UserDto userDto) throws UserNotFoundException {
        // Include mapper
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user [" + id + "] is found."));

        User newSavedUser = null;
        try {
            newSavedUser = userRepository.save(userMapper.toNewUpdateUserEntity(existingUser, userDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSavedUser;
    }

    @Transactional
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
