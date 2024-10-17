package com.bruce.services.user_service.services.extd;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.services.AbstractUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceExtd extends AbstractUser {

    @Override
    public User getUser() {
        return new User();
    }

    @Override
    public User addUser() {
        return new User();
    }

    @Override
    public User updateUser() {
        return new User();
    }

    @Override
    public User deleteUser() {
        return new User();
    }
}
