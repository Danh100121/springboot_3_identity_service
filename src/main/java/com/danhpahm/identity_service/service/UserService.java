package com.danhpahm.identity_service.service;

import com.danhpahm.identity_service.dto.request.UserCreationRequest;
import com.danhpahm.identity_service.dto.request.UserUpdateRequest;
import com.danhpahm.identity_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getMyInfo();

    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(String id);

    UserResponse updateUser(String userId, UserUpdateRequest request);

    void deleteUser(String userId);
}
