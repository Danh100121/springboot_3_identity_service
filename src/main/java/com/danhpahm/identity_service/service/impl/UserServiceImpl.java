package com.danhpahm.identity_service.service.impl;

import com.danhpahm.identity_service.constant.PredefinedRole;
import com.danhpahm.identity_service.dto.request.UserCreationRequest;
import com.danhpahm.identity_service.dto.request.UserUpdateRequest;
import com.danhpahm.identity_service.dto.response.UserResponse;
import com.danhpahm.identity_service.entity.Role;
import com.danhpahm.identity_service.entity.User;
import com.danhpahm.identity_service.exception.AppException;
import com.danhpahm.identity_service.exception.ErrorCode;
import com.danhpahm.identity_service.mapper.UserMapper;
import com.danhpahm.identity_service.repository.RoleRepository;
import com.danhpahm.identity_service.repository.UserRepository;
import com.danhpahm.identity_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepository userRepository;

    UserMapper userMapper;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    //    @PreAuthorize("hasAnyAuthority('CREATE_DATA')")
    public List<UserResponse> getUsers() {
        log.info("In method getUsers()");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @Override
    //    @PostAuthorize("returnObject.username == authentication.name")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        log.info("In method getUser() by id");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
