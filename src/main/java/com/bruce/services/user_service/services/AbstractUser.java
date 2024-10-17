package com.bruce.services.user_service.services;

import com.bruce.services.user_service.dao.User;

public abstract class AbstractUser {

    // Retrieve
    public abstract User getUser();

    // Add
    public abstract User addUser();

    // Update
    public abstract User updateUser();

    // Delete
    public abstract User deleteUser();

}
