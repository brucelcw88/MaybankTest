package com.bruce.services.user_service.mapper;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.dto.UserDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toUserEntity(UserDto userDto) {
        String currentTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        User savedUser = modelMapper.map(userDto, User.class);
        savedUser.setCreatedAt(LocalDateTime.now());
        savedUser.setCreatedBy("SYSTEM");
        savedUser.setUpdatedAt(LocalDateTime.parse(currentTimeStamp));
        savedUser.setUpdatedBy("SYSTEM");
        return savedUser;
    }

    public User toNewUpdateUserEntity(User existingUser, UserDto userDto) {
        return existingUser.toBuilder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .address(userDto.getAddress())
                .updatedAt(LocalDateTime.now())
                .updatedBy("Bruce")
                .build();
    }

    public User toFormattedTimestamp(User user) {
        return user.toBuilder()
                .createdAt(LocalDateTime.parse(
                        user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .updatedAt(LocalDateTime.parse(
                        user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }
}
