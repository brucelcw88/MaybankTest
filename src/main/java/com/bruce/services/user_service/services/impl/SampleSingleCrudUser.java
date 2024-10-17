package com.bruce.services.user_service.services.impl;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.dto.UserDto;
import com.bruce.services.user_service.services.IUser;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Deprecated
public abstract class SampleSingleCrudUser implements IUser {

    // Same as Add/Delete/Update/Get User. Current method using updateUser as sample
    @Override
    public User updateUser(UUID id, UserDto userDto) {
        return new User();
    }
}
