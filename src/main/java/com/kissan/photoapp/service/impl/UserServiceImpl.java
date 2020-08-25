package com.kissan.photoapp.service.impl;

import com.kissan.photoapp.dao.UserDAO;
import com.kissan.photoapp.service.UserService;
import com.kissan.photoapp.ui.model.request.UserRequest;
import com.kissan.photoapp.ui.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserResponse[] getAllUsers() {

        return userDAO.getAllUsers();
    }

    @Override
    public UserResponse getUser(String userId) {

        return userDAO.getUser(userId);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        return userDAO.addUser(userRequest);
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest userRequest) {
        return userDAO.updateUser(userId, userRequest);
    }

    @Override
    public boolean deleteUser(String userId) {
        return userDAO.deleteUser(userId);
    }


}
