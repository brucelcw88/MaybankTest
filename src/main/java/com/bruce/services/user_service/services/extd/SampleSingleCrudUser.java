package com.bruce.services.user_service.services.extd;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.services.AbstractUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Deprecated
public abstract class SampleSingleCrudUser extends AbstractUser {

    // Same as Add/Delete/Update/Get User. Current method using updateUser as sample
    @Override
    public User updateUser() {
        return new User();
    }
}
