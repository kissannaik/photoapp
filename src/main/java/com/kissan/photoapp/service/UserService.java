package com.kissan.photoapp.service;

import com.kissan.photoapp.ui.model.request.UserRequest;
import com.kissan.photoapp.ui.model.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse[] getAllUsers();

    UserResponse getUser(String userId);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(String userId, UserRequest userRequest);

    boolean deleteUser(String userId);
}
